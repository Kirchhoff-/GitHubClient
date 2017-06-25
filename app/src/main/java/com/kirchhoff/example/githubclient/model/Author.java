package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kirchhoff-
 */

public class Author {

    @SerializedName("name")
    private String authorName;

    @NonNull
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        this.authorName = authorName;
    }

}
