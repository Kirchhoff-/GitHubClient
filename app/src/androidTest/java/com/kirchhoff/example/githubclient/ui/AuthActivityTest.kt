package com.kirchhoff.example.githubclient.ui

import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.text.InputType
import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity
import com.kirchhoff.example.githubclient.utils.matchers.CustomMatcher
import com.kirchhoff.example.githubclient.utils.matchers.CustomViewInteraction
import com.kirchhoff.example.githubclient.utils.matchers.InputLayoutErrorMatcher.withInputError
import com.kirchhoff.example.githubclient.utils.matchers.ToastMatcher
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @author Kirchhoff-
 */

@RunWith(AndroidJUnit4::class)
class AuthActivityTest {

    @Rule @JvmField
    val activityRule = ActivityTestRule(AuthActivity::class.java)

    //Also can do like this ->
    // @get:Rule
    // val activityRule: ActivityTestRule<AuthActivity> = ActivityTestRule(AuthActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun init() {
        Intents.init()
    }

    @After
    @Throws(Exception::class)
    fun finish() {
        Intents.release()
    }

    @Test
    @Throws(Exception::class)
    fun testEmptyInputFields() {
        onView(withId(R.id.loginEdit)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText(""))))

        onView(withId(R.id.passwordEdit)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD),
                withText(""))))

        onView(withId(R.id.loginInputLayout))
                .check(matches(CustomMatcher.withInputHint(activityRule.activity
                        .getString(R.string.auth_login_hint))))

        onView(withId(R.id.passwordInputLayout))
                .check(matches(CustomMatcher.withInputHint(activityRule.activity
                        .getString(R.string.auth_password_hint))))
    }

    @Test
    @Throws(Exception::class)
    fun testLoginButtonShown() {

        onView(withId(R.id.enterButton)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.auth_enter))))
    }

    @Test
    @Throws(Exception::class)
    fun testInputDisplayed() {
        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        closeSoftKeyboard();

        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.loginEdit)).check(matches(withText(Constants.LOGIN)));

        onView(withId(R.id.passwordEdit)).check(matches(withText(Constants.PASSWORD)));
    }


    @Test
    @Throws(Exception::class)
    fun testLoginErrorDisplayed() {
        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD));
        closeSoftKeyboard();
        onView(withId(R.id.enterButton)).perform(click());

        onView(withId(R.id.loginInputLayout))
                .check(matches(withInputError(R.string.auth_login_error)));
    }

    @Test
    @Throws(Exception::class)
    fun testPasswordErrorDisplayed() {
        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        closeSoftKeyboard();
        onView(withId(R.id.enterButton)).perform(click());

        onView(withId(R.id.passwordInputLayout))
                .check(matches(withInputError(R.string.auth_password_error)));
    }

    @Test
    @Throws(Exception::class)
    fun testAuthError() {
        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN))
        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.WRONG_PASSWORD))
        closeSoftKeyboard()

        onView(withId(R.id.enterButton)).perform(click())

        onView(withText(R.string.auth_error)).inRoot(ToastMatcher())
                .check(matches(isDisplayed()))
    }

    @Test
    @Throws(Exception::class)
    fun testSuccessAuth() {
        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN))
        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD))
        closeSoftKeyboard()

        onView(withId(R.id.enterButton)).perform(click())
    }

    @Test
    @Throws(Exception::class)
    fun testTitle() {
        CustomViewInteraction.matchToolbarTitle(activityRule.activity.getString(R.string.auth_title))
                .check(matches(isDisplayed()));
    }
}
