package com.onwd.challenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.devices.IDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DeviceFragmentViewModel @Inject constructor(
    private val startSearch: StartSearch,
    registerListenerForSearch: RegisterListenerForSearch
) : ViewModel() {

    private val _devices: MutableStateFlow<List<IDevice>> = MutableStateFlow(listOf())
    init {
        viewModelScope.launch {
            _devices.collect {
                Timber.e("devices list: $it")
            }
        }

        registerListenerForSearch {
            // Creating a local tmp list might be avoided if a list of IDevice was returned by the Listener
            val currentList = _devices.value.toMutableList()
            currentList.add(it)
            viewModelScope.launch {
                _devices.emit(currentList)
            }
        }
    }

    fun startSearchDevices() {
        viewModelScope.launch {
            startSearch()
        }
    }

}