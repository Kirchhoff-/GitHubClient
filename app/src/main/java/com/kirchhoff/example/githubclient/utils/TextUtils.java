package com.kirchhoff.example.githubclient.utils;

import android.support.annotation.Nullable;

/**
 * @author Kirchhoff-
 */

public class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

}
