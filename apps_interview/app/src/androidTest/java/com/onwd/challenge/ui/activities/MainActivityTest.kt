package com.onwd.challenge.ui.activities

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.onwd.challenge.ui.viewmodels.DeviceFragmentViewModel
import dagger.hilt.android.testing.*
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    lateinit var context: Context

    @BindValue
    @JvmField
    val viewModel = mockk<DeviceFragmentViewModel>(relaxed = true)

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext<HiltTestApplication>()
    }

    @Test
    fun check_the_activity_is_starting_correctly() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        Assert.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }
}