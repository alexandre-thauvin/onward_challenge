package com.onwd.challenge.internal

import com.onwd.challenge.repositories.DeviceInteractorRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class StartSearchImplTest {

    @MockK
    lateinit var repositoryMock: DeviceInteractorRepository

    private lateinit var startSearchImpl: StartSearchImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        startSearchImpl = StartSearchImpl(repositoryMock)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should launch the search`(){
        every { repositoryMock.startSearchingForDevices() }.just(Runs)

        startSearchImpl()

        verify { repositoryMock.startSearchingForDevices() }
    }
}