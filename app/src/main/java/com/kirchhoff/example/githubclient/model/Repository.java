package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kirchhoff-
 */

public class Repository {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    @SerializedName("stargazers_count")
    private int starsCount;

    @SerializedName("forks_count")
    private int forksCount;

    @SerializedName("watchers_count")
    private int watchersCount;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description = description == null ? "" : description;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        language = language == null ? "" : language;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        starsCount = starsCount < 0 ? 0 : starsCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        forksCount = forksCount < 0 ? 0 : forksCount;
    }

    public int getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(int watchersCount) {
        watchersCount = watchersCount < 0 ? 0 : watchersCount;
    }

}
