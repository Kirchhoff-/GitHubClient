package com.kirchhoff.example.githubclient.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kirchhoff.example.githubclient.Constants;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity;
import com.kirchhoff.example.githubclient.utils.matchers.ToastMatcher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * @author Kirchhoff-
 */

@RunWith(AndroidJUnit4.class)
public class RepositoriesActivityTest {

    @Rule
    public final ActivityTestRule<RepositoriesActivity> activityRule =
            new ActivityTestRule<>(RepositoriesActivity.class, false, false);


    @Test
    public void testErrorRepositories() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.ERROR;

        launchActivity();
        onView(withText(R.string.repositories_error)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

        onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())));
    }


    @Test
    public void testEmptyRepositories() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.EMPTY;

        launchActivity();
        onView(withId(R.id.emptyTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.emptyTextView)).check(matches(withText(R.string.repositories_empty)));
    }

    @Test
    public void testSuccessRepositories() throws Exception {
        Constants.DATA_TEST_ENUM = Constants.DataTestEnum.DATA;

        launchActivity();
        onView(withId(R.id.emptyTextView)).check(matches(not(isDisplayed())));
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));

        onView(withId(R.id.recyclerView)).perform(scrollToPosition(6))
                .perform(scrollToPosition(0))
                .perform(scrollToPosition(5))
                .perform(scrollToPosition(1));
    }

    private void launchActivity() {
        Context context = InstrumentationRegistry.getContext();
        Intent intent = new Intent(context, RepositoriesActivity.class);
        activityRule.launchActivity(intent);
    }
}
