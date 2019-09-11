package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("author")
    private Author author;

    @SerializedName("message")
    private String message;

    Commit() {
        author = new Author();
        message = "";
    }

    @NonNull
    public Author getAuthor() {
        return author;
    }

    @NonNull
    public String getMessage() {
        return message;
    }
}
