package com.onwd.challenge.repositories

import com.onwd.devices.IDeviceFoundListener
import com.onwd.devices.IDeviceInteractor
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

class DeviceInteractorRepositoryTest {

    @MockK
    lateinit var iDeviceFoundListenerMock: IDeviceFoundListener

    @MockK
    lateinit var deviceInteractorMock: IDeviceInteractor

    private lateinit var deviceInteractorRepository: DeviceInteractorRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        deviceInteractorRepository = DeviceInteractorRepository(deviceInteractorMock)
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    @Test
    fun `should start searching`(){
        every { deviceInteractorMock.startSearch() }.just(Runs)

        deviceInteractorRepository.startSearchingForDevices()

        verify { deviceInteractorMock.startSearch() }
    }

    @Test
    fun `should register listener`(){
        every { deviceInteractorMock.registerListener(iDeviceFoundListenerMock) }.just(Runs)

        deviceInteractorRepository.registerListener(iDeviceFoundListenerMock)

        verify { deviceInteractorMock.registerListener(iDeviceFoundListenerMock) }
    }

}