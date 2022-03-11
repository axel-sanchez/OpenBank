package com.example.openbank.presentation.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.openbank.R
import com.example.openbank.presentation.adapters.CharacterAdapter
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class CharacterFragmentTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun should_show_recyclerview_and_hide_progress_and_message() {
        Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.progress)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.empty_state)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun should_show_product_details_when_click_item() {
        Espresso.onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<CharacterAdapter.ViewHolder>(2, click()))
        Espresso.onView(withId(R.id.tvName)).check(ViewAssertions.matches(ViewMatchers.withText("A.I.M.")))
    }

    @Test
    fun should_show_recyclerview_when_press_back_from_details_fragment() {
        Espresso.onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<CharacterAdapter.ViewHolder>(2, click()))
        Espresso.onView(withId(R.id.tvName)).check(ViewAssertions.matches(ViewMatchers.withText("A.I.M.")))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.progress)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(withId(R.id.empty_state)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }
}