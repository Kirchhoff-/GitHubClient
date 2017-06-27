package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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

    //Simplify for now
    //I know that if you override equels you also need to override hashCode
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Repository repository = (Repository) obj;
        return name.equals(repository.name);
    }
}
