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

    //Just for test
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        return true;
    }
}
