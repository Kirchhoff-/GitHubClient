package com.kirchhoff.example.githubclient.ui;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity;
import com.kirchhoff.example.githubclient.utils.matchers.ToastMatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.kirchhoff.example.githubclient.utils.matchers.CustomMatcher.withInputHint;
import static com.kirchhoff.example.githubclient.utils.matchers.CustomViewInteraction.matchToolbarTitle;
import static com.kirchhoff.example.githubclient.utils.matchers.InputLayoutErrorMatcher.withInputError;
import static org.hamcrest.core.AllOf.allOf;

/**
 * @author Kirchhoff-
 */

@RunWith(AndroidJUnit4.class)
public class AuthActivityTest {

    @Rule
    public final ActivityTestRule<AuthActivity> activityRule =
            new ActivityTestRule<>(AuthActivity.class);

    @Before
    public void setUp() throws Exception {
        Intents.init();
    }

    @After
    public void finish() throws Exception {
        Intents.release();
    }

    @Test
    public void testEmptyInputFields() throws Exception {
        testTitle();

        onView(withId(R.id.loginEdit)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withText(""))));

        onView(withId(R.id.passwordEdit)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isFocusable(),
                isClickable(),
                withInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD),
                withText(""))));

        onView(withId(R.id.loginInputLayout))
                .check(matches(withInputHint(activityRule.getActivity()
                        .getString(R.string.auth_login_hint))));

        onView(withId(R.id.passwordInputLayout))
                .check(matches(withInputHint(activityRule.getActivity()
                        .getString(R.string.auth_password_hint))));
    }

    @Test
    public void testLoginButtonShown() throws Exception {
        testTitle();

        onView(withId(R.id.enterButton)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.auth_enter)
        )));
    }

    @Test
    public void testInputDisplayed() throws Exception {
        testTitle();

        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        closeSoftKeyboard();

        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.loginEdit)).check(matches(withText(Constants.LOGIN)));

        onView(withId(R.id.passwordEdit)).check(matches(withText(Constants.PASSWORD)));
    }

    @Test
    public void testLoginErrorDisplayed() throws Exception {
        testTitle();

        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD));
        closeSoftKeyboard();
        onView(withId(R.id.enterButton)).perform(click());

        onView(withId(R.id.loginInputLayout))
                .check(matches(withInputError(R.string.auth_login_error)));
    }

    @Test
    public void testPasswordErrorDisplayed() throws Exception {
        testTitle();

        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        closeSoftKeyboard();
        onView(withId(R.id.enterButton)).perform(click());

        onView(withId(R.id.passwordInputLayout))
                .check(matches(withInputError(R.string.auth_password_error)));
    }

    @Test
    public void testErrorAuth() throws Exception {
        testTitle();

        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.WRONG_PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.enterButton)).perform(click());

        onView(withText(R.string.auth_error)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void testSuccessAuth() throws Exception {
        testTitle();

        onView(withId(R.id.loginEdit)).perform(typeText(Constants.LOGIN));
        onView(withId(R.id.passwordEdit)).perform(typeText(Constants.PASSWORD));
        closeSoftKeyboard();

        onView(withId(R.id.enterButton)).perform(click());

        Intents.intended(hasComponent(RepositoriesActivity.class.getName()));
    }

    private void testTitle() throws Exception {
        matchToolbarTitle(activityRule.getActivity().getString(R.string.auth_title))
                .check(matches(isDisplayed()));
    }

}
