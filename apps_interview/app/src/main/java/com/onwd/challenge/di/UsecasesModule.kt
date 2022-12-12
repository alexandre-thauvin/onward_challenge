package com.onwd.challenge.di

import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.challenge.internal.RegisterListenerFoSearchImpl
import com.onwd.challenge.internal.StartSearchImpl
import com.onwd.challenge.repositories.DeviceInteractorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UsecasesModule {

    @Provides
    fun providesStartSearch(deviceInteractorRepository: DeviceInteractorRepository): StartSearch = StartSearchImpl(deviceInteractorRepository)

    @Provides
    fun providesRegisterListener(deviceInteractorRepository: DeviceInteractorRepository): RegisterListenerForSearch = RegisterListenerFoSearchImpl(deviceInteractorRepository)
}