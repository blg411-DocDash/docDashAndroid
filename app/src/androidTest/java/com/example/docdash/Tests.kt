package com.example.docdash

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.docdash.ui.login.LoginActivity
import com.example.docdash.ui.myTasks.MyTasksAcitivity
import com.example.docdash.ui.patientDetails.PatientDetails
import com.example.docdash.ui.patientDetails.PatientDetailsActivity
import com.example.docdash.ui.taskDetails.TaskDetailsActivity
import com.example.docdash.ui.taskPool.TaskPoolActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class Tests {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()


    fun login(){
        onView(withId(R.id.editTextEmailAddress))
            .perform(typeText("hacer@docdash.com"))

        onView(withId(R.id.editTextPassword))
            .perform(typeText("hacer@docdash.com"))

        // Replace R.id.buttonLogin with the actual ID of your login button
        onView(withId(R.id.buttonLogin))
            .perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun loginSuccess() {
        logout()
        login()
        Thread.sleep(2000)
        onView(withId(R.id.buttonMyTasks))
            .check(matches(isDisplayed()))
    }

    @Test
    fun taskSelectSuccess() {
        Intents.init()
        login()
        Thread.sleep(5000)
        onView(withId(R.id.taskPoolRW))
            .perform(ViewActions.click())

        Thread.sleep(2000)
        Intents.intended(IntentMatchers.hasComponent(TaskDetailsActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun takeTaskSuccess() {
        logout()
        login()

        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.taskPoolRW))
            .perform(ViewActions.click())

        Thread.sleep(2000)
        composeTestRule.onNodeWithTag("takeTask").performClick()

    }

    @Test
    fun patientPageSuccess(){
        logout()
        login()
        Intents.init()
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.taskPoolRW))
            .perform(ViewActions.click())
        composeTestRule.onNodeWithTag("patient").performClick()
        Intents.intended(IntentMatchers.hasComponent(PatientDetailsActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun myTasksPageSuccess(){
        Thread.sleep(2000)
        Intents.init()
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.buttonMyTasks))
            .perform(ViewActions.click())
        Thread.sleep(5000)
        Intents.intended(IntentMatchers.hasComponent(MyTasksAcitivity::class.java.name))
        Intents.release()
    }

    @Test
    fun logoutSuccesful(){
        login()
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.profileButton))
            .perform(ViewActions.click())
        Thread.sleep(5000)
        onView(withId(com.example.docdash.R.id.buttonLogout))
            .perform(ViewActions.click())
        Thread.sleep(5000)

        onView(withId(R.id.editTextEmailAddress))
            .check(matches(isDisplayed()))

    }
    fun logout(){
        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.profileButton))
            .perform(ViewActions.click())
        Thread.sleep(5000)
        onView(withId(com.example.docdash.R.id.buttonLogout))
            .perform(ViewActions.click())
        Thread.sleep(5000)
    }

}