package com.kirchhoff.example.githubclient.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kirchhoff-
 */

public class CommitResponse {

    @SerializedName("commit")
    private Commit commit;

    @NonNull
    public Commit getCommit() {
        return commit;
    }


}
