package com.example.docdash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.docdash.ui.login.LoginActivity
import com.example.docdash.ui.taskPool.TaskPoolActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@LargeTest
class CheckLogin {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test fun loginSuccess() {
        Intents.init()
        onView(withId(R.id.editTextEmailAddress))
            .perform(typeText("hacer@docdash.com"))

        onView(withId(R.id.editTextPassword))
            .perform(typeText("hacer@docdash.com"))

        // Replace R.id.buttonLogin with the actual ID of your login button
        onView(withId(R.id.buttonLogin))
            .perform(click())

        Thread.sleep(2000)
        intended(hasComponent(TaskPoolActivity::class.java.name))
        Intents.release()
    }
}