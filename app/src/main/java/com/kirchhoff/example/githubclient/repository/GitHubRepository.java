package com.kirchhoff.example.githubclient.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.api.ApiFactory;
import com.kirchhoff.example.githubclient.model.Authorization;
import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.utils.AuthorizationUtils;

import java.util.List;

import rx.Observable;

public class GitHubRepository implements GitHubDataSource {

    @Nullable
    private static GitHubRepository INSTANCE = null;

    private GitHubRepository() {
    }

    public static GitHubRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GitHubRepository();
        }

        return INSTANCE;
    }

    @NonNull
    @Override
    public Observable<Authorization> auth(@NonNull String login, @NonNull String password) {

        String authorizationString = AuthorizationUtils.createAuthorizationString(login, password);

        return ApiFactory.getGitHubService().authorize(authorizationString,
                AuthorizationUtils.createAuthorizationParam()).doOnNext(authorization -> ApiFactory.recreate());
    }

    @NonNull
    @Override
    public Observable<List<Repository>> repositories() {
        return ApiFactory.getGitHubService().repositories();
    }

    @NonNull
    @Override
    public Observable<List<CommitResponse>> getCommits(@NonNull String repos) {
        return ApiFactory.getGitHubService()
                .commits(Injection.provideKeyValueStorage().getUserName(), repos);
    }

    @Override
    public void logout() {
        ApiFactory.recreate();
    }
}
