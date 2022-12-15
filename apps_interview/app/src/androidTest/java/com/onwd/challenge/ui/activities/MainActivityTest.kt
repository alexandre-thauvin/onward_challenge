package com.onwd.challenge.ui.activities

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.onwd.challenge.CoroutineTestRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    lateinit var context: Context

    @Before
    fun before() {
        context = ApplicationProvider.getApplicationContext<HiltTestApplication>()
    }

    @Test
    fun check_the_activity_is_starting_correctly() = runTest {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        delay(5000)
        Assert.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }
}