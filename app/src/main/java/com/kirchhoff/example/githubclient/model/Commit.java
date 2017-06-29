package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kirchhoff-
 */

public class Commit {

    private String repoName;

    @SerializedName("author")
    private Author author;

    @SerializedName("message")
    private String message;

    public Commit() {
        author = new Author();
        message = "";
        repoName = "";
    }

    @NonNull
    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(@NonNull String repoName) {
        this.repoName = repoName;
    }

    @NonNull
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull Author author) {
        this.author = author;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public void setMessage(@NonNull String message) {
        this.message = message;
    }

}
