package com.onwd.challenge.ui.viewmodels

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.devices.IDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class DeviceFragmentViewModel @Inject constructor(
    private val startSearch: StartSearch,
    registerListenerForSearch: RegisterListenerForSearch
) : ViewModel() {

    //Instead of emitting a flow of data directly we could replace it with a UIState containing these data
    private val _devicesFlow: MutableSharedFlow<List<IDevice>> = MutableSharedFlow(replay = 1)
    val devicesFlow: SharedFlow<List<IDevice>> = _devicesFlow

    var currentItem = -1

    //Can be avoided if the api returns a list of IDevice instead of IDevice
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val devices = mutableListOf<IDevice>()

    init {
        registerListenerForSearch {
            viewModelScope.launch {
                Timber.d("device received")
                devices.add(it)
                if (!_devicesFlow.tryEmit(devices))
                    devices.remove(it)
            }
        }
    }

    fun startSearchDevices() {
        //we clear the list on each new research otherwise we might have duplicate devices
        devices.clear()
        viewModelScope.launch {
            startSearch()
        }
    }

    fun createBundle(): Bundle {
        val device = devices[currentItem]
        val bundle = Bundle()
        bundle.putSerializable(CURRENT_DEVICE, device)
        return bundle
    }

    companion object {
        const val CURRENT_DEVICE = "CURRENT_DEVICE"
    }
}