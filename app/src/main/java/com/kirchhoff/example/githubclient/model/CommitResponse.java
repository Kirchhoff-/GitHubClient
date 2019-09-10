package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class CommitResponse {

    @SerializedName("commit")
    private Commit commit;

    public CommitResponse() {
        commit = new Commit();
    }

    @NonNull
    public Commit getCommit() {
        return commit;
    }
}
