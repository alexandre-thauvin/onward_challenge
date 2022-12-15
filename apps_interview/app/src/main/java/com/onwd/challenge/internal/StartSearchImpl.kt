package com.onwd.challenge.internal

import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.challenge.repositories.DeviceInteractorRepository
import javax.inject.Inject

internal class StartSearchImpl
@Inject constructor(private val deviceInteractorRepository: DeviceInteractorRepository) : StartSearch {
    override fun invoke() {
        deviceInteractorRepository.startSearchingForDevices()
    }
}