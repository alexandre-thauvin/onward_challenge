package com.onwd.challenge.internal

import com.onwd.challenge.internal.RegisterListenerFoSearchImpl
import com.onwd.challenge.internal.StartSearchImpl
import com.onwd.challenge.repositories.DeviceInteractorRepository
import com.onwd.devices.IDeviceFoundListener
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RegisterListenerForSearchImplTest {

    @MockK
    lateinit var iDeviceFoundListenerMock: IDeviceFoundListener

    @MockK
    lateinit var repositoryMock: DeviceInteractorRepository

    private lateinit var registerListenerFoSearchImpl: RegisterListenerFoSearchImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        registerListenerFoSearchImpl = RegisterListenerFoSearchImpl(repositoryMock)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should register the listener`(){
        every { repositoryMock.registerListener(iDeviceFoundListenerMock) }.just(Runs)

        registerListenerFoSearchImpl(iDeviceFoundListenerMock)

        verify { repositoryMock.registerListener(iDeviceFoundListenerMock) }
    }

}