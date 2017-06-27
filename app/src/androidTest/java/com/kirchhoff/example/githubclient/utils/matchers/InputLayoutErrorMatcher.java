package com.kirchhoff.example.githubclient.utils.matchers;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author Kirchhoff-
 */

public class InputLayoutErrorMatcher extends TypeSafeMatcher<View> {

    @StringRes
    private final int errorTextId;
    private String expectedError;

    private InputLayoutErrorMatcher(@StringRes int errorTextId) {
        this.errorTextId = errorTextId;
    }

    @NonNull
    public static InputLayoutErrorMatcher withInputError(@StringRes int errorTextId) {
        return new InputLayoutErrorMatcher(errorTextId);
    }

    @Override
    protected boolean matchesSafely(View item) {
        if (!(item instanceof TextInputLayout))
            return false;

        expectedError = item.getResources().getString(errorTextId);
        TextInputLayout layout = (TextInputLayout) item;
        return TextUtils.equals(expectedError, layout.getError());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("with error: " + expectedError);
    }
}
