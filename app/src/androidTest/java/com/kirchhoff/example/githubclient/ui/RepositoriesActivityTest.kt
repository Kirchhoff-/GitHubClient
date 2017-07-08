package com.kirchhoff.example.githubclient.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesViewHolder
import com.kirchhoff.example.githubclient.utils.matchers.ToastMatcher
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Kirchhoff-
 */

@RunWith(AndroidJUnit4::class)
class RepositoriesActivityTest {


    @Rule @JvmField
    val activityRule = ActivityTestRule(RepositoriesActivity::class.java)


    @Before
    @Throws(Exception::class)
    fun init() {
        Intents.init()
    }

    @Before
    @Throws(Exception::class)
    fun finish() {
        Intents.release()
    }

    @Test
    @Throws(Exception::class)
    fun testErrorRepositories() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR

        launchActivity()

        onView(withText(R.string.repositories_error)).inRoot(ToastMatcher())
                .check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())))
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyRepositories() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY

        launchActivity()
        onView(withId(R.id.emptyTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.emptyTextView)).check(matches(withText(R.string.repositories_empty)))
    }

    @Test
    @Throws(Exception::class)
    fun testSuccessRepositories() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA

        launchActivity()
        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).perform(scrollToPosition<RepositoriesViewHolder>(6))
                .perform(scrollToPosition<RepositoriesViewHolder>(0))
                .perform(scrollToPosition<RepositoriesViewHolder>(5))
                .perform(scrollToPosition<RepositoriesViewHolder>(1))
    }

    private fun launchActivity() {
        val context = InstrumentationRegistry.getContext()
        val intent = Intent(context, RepositoriesActivity::class.java)
        activityRule.launchActivity(intent)
    }
}