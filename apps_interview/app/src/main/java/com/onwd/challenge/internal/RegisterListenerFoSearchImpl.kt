package com.onwd.challenge.internal

import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.repositories.DeviceInteractorRepository
import com.onwd.devices.IDeviceFoundListener
import javax.inject.Inject

internal class RegisterListenerFoSearchImpl
@Inject constructor(private val deviceInteractorRepository: DeviceInteractorRepository) : RegisterListenerForSearch {
    override fun invoke(deviceFoundListener: IDeviceFoundListener) {
        deviceInteractorRepository.registerListener(deviceFoundListener)
    }
}