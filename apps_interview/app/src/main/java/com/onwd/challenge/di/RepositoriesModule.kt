package com.onwd.challenge.di

import com.onwd.challenge.repositories.DeviceInteractorRepository
import com.onwd.devices.IDeviceInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoriesModule {

    @Provides
    @Singleton
    fun providesDeviceInteractorRepository(iDeviceInteractor: IDeviceInteractor): DeviceInteractorRepository = DeviceInteractorRepository(iDeviceInteractor)
}