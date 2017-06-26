package com.kirchhoff.example.githubclient.repository;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.Authorization;
import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.model.Repository;

import java.util.List;

import rx.Observable;

/**
 * @author Kirchhoff-
 */

public interface GitHubDataSource {

    @NonNull
    Observable<Authorization> auth(@NonNull String login, @NonNull String password);

    @NonNull
    Observable<List<Repository>> repositories();

    @NonNull
    Observable<List<CommitResponse>> getCommits(@NonNull String repos);
}
