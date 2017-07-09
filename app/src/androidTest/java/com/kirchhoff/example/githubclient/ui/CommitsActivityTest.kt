package com.kirchhoff.example.githubclient.ui

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.ui.commit.CommitsActivity
import com.kirchhoff.example.githubclient.ui.commit.CommitsViewHolder
import com.kirchhoff.example.githubclient.utils.matchers.ToastMatcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Kirchhoff-
 */
@RunWith(AndroidJUnit4::class)
class CommitsActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(CommitsActivity::class.java, false, false)


    @Test
    @Throws(Exception::class)
    fun testErrorCommits() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR

        launchActivity()

        onView(withText(R.string.commits_error)).inRoot(ToastMatcher())
                .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())))
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyCommits() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY

        launchActivity()

        onView(withId(R.id.emptyTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.emptyTextView)).check(matches(withText(R.string.commits_empty)))
    }

    @Test
    @Throws(Exception::class)
    fun testSuccessCommits() {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA

        launchActivity()

        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).perform(scrollToPosition<CommitsViewHolder>(6))
                .perform(scrollToPosition<CommitsViewHolder>(0))
                .perform(scrollToPosition<CommitsViewHolder>(5))
                .perform(scrollToPosition<CommitsViewHolder>(1))
    }

    private fun launchActivity() {
        val context = InstrumentationRegistry.getContext()
        val intent = Intent(context, CommitsActivity::class.java)
        intent.putExtra(CommitsActivity.REPOSITORY_ARG, Constants.emulateRepository().name)
        activityRule.launchActivity(intent)
    }
}