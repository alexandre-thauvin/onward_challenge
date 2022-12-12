package com.onwd.challenge.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.devices.IDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceFragmentViewModel @Inject constructor(
    val startSearch: StartSearch,
    registerListenerForSearch: RegisterListenerForSearch
) : ViewModel() {

    init {
        registerListenerForSearch {
            // Creating a local tmp list might be avoided if a list of IDevice was returned by the Listener
            val currentList = _devices.value.toMutableList()
            currentList.add(it)
            viewModelScope.launch {
                _devices.emit(currentList)
            }
        }
    }

    private val _devices: MutableStateFlow<List<IDevice>> = MutableStateFlow(listOf())
    val devices: StateFlow<List<IDevice>> = _devices

    fun startSearchDevices() {
        viewModelScope.launch {
            startSearch()
        }
    }
}