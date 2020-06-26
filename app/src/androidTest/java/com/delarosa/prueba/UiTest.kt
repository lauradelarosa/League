package com.delarosa.prueba

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UiTest {

    @Rule
    @JvmField
    var customActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private var device: UiDevice? = null

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun getToTeam() {
        goToTeam(device!!, 1)
        isTextDisplayed("Choose a team")
    }

    @Test
    fun getToTeamDetail() {
        goToDetails(device!!, 1)
        isTextDisplayed("1991")
    }

    @Test
    fun getToLeague() {
        goToLeague(device!!, 1)
        isTextDisplayed("Welcome, choose a league")
    }

}
