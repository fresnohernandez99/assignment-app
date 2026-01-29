package com.fresnohernandez.assignmentapp.ui.starting

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fresnohernandez.assignmentapp.R
import com.fresnohernandez.assignmentapp.ui.main.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginButton_initiallyDisabled() {
        onView(withId(R.id.loginBtn)).check(matches(not(isEnabled())))
    }

    @Test
    fun loginButton_enabledWhenFieldsAreValid() {
        onView(withId(R.id.etEmail)).perform(typeText("test@example.com"), closeSoftKeyboard())

        onView(withId(R.id.etPassword)).perform(typeText("123456"), closeSoftKeyboard())

        onView(withId(R.id.loginBtn)).check(matches(isEnabled()))
    }

    @Test
    fun loadingView_isShownDuringLogin() {
        onView(withId(R.id.etEmail)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.etPassword)).perform(typeText("123456"), closeSoftKeyboard())

        onView(withId(R.id.loginBtn)).perform(click())

        onView(withId(R.id.loadingFrame)).check(matches(isDisplayed()))
    }
}
