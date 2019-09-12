package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public final class Repository {

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

    public Repository(@NonNull String name, @Nullable String description,
                      @NonNull String language, int starsCount, int forksCount,
                      int watchersCount) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.starsCount = starsCount;
        this.forksCount = forksCount;
        this.watchersCount = watchersCount;
    }

    public Repository() {
        this.name = "Default Name";
        this.description = "Default Description";
        this.language = "Default language";
        this.starsCount = 0;
        this.forksCount = 0;
        this.watchersCount = 0;
    }

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

    @NonNull
    public String getLanguage() {
        return language;
    }

    public int getStarsCount() {
        return starsCount;
    }


    public int getForksCount() {
        return forksCount;
    }


    public int getWatchersCount() {
        return watchersCount;
    }
}
