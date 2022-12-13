package com.onwd.challenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.devices.IDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceFragmentViewModel @Inject constructor(
    private val startSearch: StartSearch,
    registerListenerForSearch: RegisterListenerForSearch
) : ViewModel() {

    private val _devicesFlow: MutableSharedFlow<List<IDevice>> = MutableSharedFlow(replay = 1)
    val devicesFlow: SharedFlow<List<IDevice>> = _devicesFlow

    //Can be avoided if the api returns a list of IDevice instead of IDevice
    private val devices = mutableListOf<IDevice>()
    init {
        registerListenerForSearch {
            viewModelScope.launch {
                devices.add(it)
                _devicesFlow.emit(devices)
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

}