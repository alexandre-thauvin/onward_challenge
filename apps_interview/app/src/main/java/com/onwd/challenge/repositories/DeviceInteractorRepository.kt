package com.onwd.challenge.repositories

import com.onwd.devices.IDeviceFoundListener
import com.onwd.devices.IDeviceInteractor
import javax.inject.Inject

//I followed the wording "Interactor" even if it doesn't exist, might be worth renaming
internal class DeviceInteractorRepository  @Inject constructor(private val iDeviceInteractor: IDeviceInteractor) {
    fun startSearchingForDevices(){
        iDeviceInteractor.startSearch()
    }

    fun registerListener(iDeviceFoundListener: IDeviceFoundListener){
        iDeviceInteractor.registerListener(iDeviceFoundListener)
    }
}