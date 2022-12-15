package com.onwd.challenge.ui.viewmodels

import app.cash.turbine.test
import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.challenge.utils.CoroutineTestRule
import com.onwd.devices.DeviceFactory
import com.onwd.devices.IDevice
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalCoroutinesApi::class)
internal class FragmentDeviceViewModelTest {

    @get:Rule(order = 1)
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var startSearchMock: StartSearch

    @RelaxedMockK
    lateinit var registerListenerForSearchMock: RegisterListenerForSearch

    private lateinit var devicesFlow: MutableSharedFlow<List<IDevice>>

    @Before
    fun setUp() {
        devicesFlow = MutableSharedFlow()
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should clear the list `() = runTest {
        coEvery { startSearchMock() }.just(Runs)

        val viewModel = obtainViewModel()
        viewModel.devices.add(DeviceFactory.createRandomDevice(Random()))

        assertEquals(1, viewModel.devices.size)

        viewModel.startSearchDevices()

        advanceUntilIdle()

        coVerify { startSearchMock() }
    }

    @Test
    fun `should register the listener on init`(){
        obtainViewModel()
        verify { registerListenerForSearchMock(any()) }
    }

    @OptIn(ExperimentalTime::class)
    @FlowPreview
    @Test
    fun `should emit flow and have devices filled`() = runTest {
        val devices = mutableListOf<IDevice>(DeviceFactory.createRandomDevice(Random()))
        val viewModel = obtainViewModel()

        viewModel.onDeviceFoundListener(devices.first())

        advanceUntilIdle()
        viewModel.devicesFlow.test {
            devicesFlow.emit(devices)
            assertEquals(
                devices,
                this.awaitItem()
            )
            cancel()
        }
        assertEquals(viewModel.devices, devices)
    }

    @Test
    fun `should return selected device`() = runTest {
        val expectedDevice = DeviceFactory.createRandomDevice(Random())

        val viewModel = obtainViewModel()
        viewModel.devices.add(expectedDevice)
        viewModel.currentItem = 0

        val actualDevice = viewModel.getSelectedDevice()

        assertEquals(
            expectedDevice,
            actualDevice)
    }

    private fun obtainViewModel(): DeviceFragmentViewModel {
        return DeviceFragmentViewModel(
            startSearch = startSearchMock,
            registerListenerForSearch = registerListenerForSearchMock
        )
    }
}