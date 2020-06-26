package com.delarosa.prueba

import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector
import junit.framework.Assert

fun isViewDisplayed(id: Int) {
    var isDisplayed = true
    Espresso.onView(ViewMatchers.withId(id))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    Assert.assertTrue(isDisplayed)
}

fun wait(seconds: Int) {
    Thread.sleep(seconds.toLong() * 1000)
}

fun recyclerViewItemClick(
    device: UiDevice,
    pos: Int
) {
    val recyclerView: UiObject =
        device.findObject(UiSelector().className("androidx.recyclerview.widget.RecyclerView"))
    Assert.assertTrue(recyclerView.exists())

    recyclerView.getChild(UiSelector().index(pos)).click()
}

fun isTextDisplayed(text: String) {
    var isDisplayed = true
    Espresso.onView(ViewMatchers.withSubstring(text))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    Assert.assertTrue(isDisplayed)
}

fun goToTeam(device: UiDevice, pos: Int = 0) {
    wait(2)
    isViewDisplayed(R.id.recycler_league)
    recyclerViewItemClick(device, pos)
    wait(2)
}

fun goToLeague(device: UiDevice, pos: Int = 0) {
    wait(2)
    isViewDisplayed(R.id.recycler_league)
}

fun goToDetails(device: UiDevice, pos: Int = 0) {
    goToTeam(device,pos)
    isViewDisplayed(R.id.recycler_team)
    recyclerViewItemClick(device, pos)
    wait(2)
}