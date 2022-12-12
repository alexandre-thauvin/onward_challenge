package com.onwd.devices.di

import com.onwd.devices.DeviceInteractorStub
import com.onwd.devices.IDeviceInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeviceInterfaceModule {

    @Provides
    fun providesDeviceInteractor(): IDeviceInteractor = DeviceInteractorStub()
}