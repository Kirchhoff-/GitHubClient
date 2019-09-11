package com.kirchhoff.example.githubclient.utils;

import android.support.annotation.Nullable;

public class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(@Nullable CharSequence text) {
        return text == null || text.length() == 0;
    }

}
