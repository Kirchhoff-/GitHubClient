package com.kirchhoff.example.githubclient.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.model.Authorization;
import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.model.Repository;

import java.util.List;

import rx.Observable;

/**
 * @author Kirchhoff-
 */

public class FakeGitHubRepository implements GitHubDataSource {

    @Nullable
    private static FakeGitHubRepository INSTANCE = null;

    private FakeGitHubRepository() {
    }

    public static FakeGitHubRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeGitHubRepository();
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {
        return null;
    }

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return null;
    }

    @NonNull
    @Override
    public Observable<List<CommitResponse>> getCommits(@NonNull String repos) {
        return null;
    }
}
