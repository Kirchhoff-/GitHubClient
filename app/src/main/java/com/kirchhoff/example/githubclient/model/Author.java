package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Author {

    @SerializedName("name")
    private String authorName;

    @NonNull
    public String getAuthorName() {
        return authorName;
    }
}
