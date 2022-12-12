package com.onwd.challenge.repositories

import com.onwd.devices.IDeviceFoundListener
import com.onwd.devices.IDeviceInteractor
import javax.inject.Inject

internal class DeviceInteractorRepository  @Inject constructor(private val iDeviceInteractor: IDeviceInteractor) {
    fun startSearchingForDevices(){
        iDeviceInteractor.startSearch()
    }

    fun registerListener(iDeviceFoundListener: IDeviceFoundListener){
        iDeviceInteractor.registerListener(iDeviceFoundListener)
    }
}