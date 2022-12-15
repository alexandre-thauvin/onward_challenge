package com.onwd.challenge.ui.viewmodels

import com.onwd.challenge.api.usecases.RegisterListenerForSearch
import com.onwd.challenge.api.usecases.StartSearch
import com.onwd.challenge.utils.CoroutineTestRule
import com.onwd.devices.DeviceFactory
import com.onwd.devices.IDevice
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class FragmentDeviceViewModelTest {

    @get:Rule(order = 1)
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    lateinit var startSearchMock: StartSearch

    @MockK
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
        coEvery { registerListenerForSearchMock(any()) }.just(Runs)

        val viewModel = obtainViewModel()
        viewModel.devices.add(DeviceFactory.createRandomDevice(Random()))

        assertEquals(1, viewModel.devices.size)

        viewModel.startSearchDevices()

        advanceUntilIdle()

        coVerify { startSearchMock() }
    }

    @Test
    fun `should return selected device`() = runTest {
        val expectedDevice = DeviceFactory.createRandomDevice(Random())
        coEvery { registerListenerForSearchMock(any()) }.just(Runs)

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