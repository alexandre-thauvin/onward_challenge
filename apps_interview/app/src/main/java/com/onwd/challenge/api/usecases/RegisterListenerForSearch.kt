package com.onwd.challenge.api.usecases

import com.onwd.devices.IDeviceFoundListener

interface RegisterListenerForSearch {
    operator fun invoke(deviceFoundListener: IDeviceFoundListener)
}