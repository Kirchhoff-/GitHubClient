package com.kirchhoff.example.githubclient.ui.auth;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.utils.TextUtils;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Kirchhoff-
 */

public class AuthPresenter implements AuthContract.Presenter {

    @NonNull
    private final GitHubDataSource repository;

    @NonNull
    private final AuthContract.View view;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;


    public AuthPresenter(@NonNull GitHubDataSource repository,
                         @NonNull AuthContract.View view,
                         @NonNull BaseSchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.subscription = new CompositeSubscription();
    }


    @Override
    public void auth(@Nullable String login, @Nullable String password) {
        if (TextUtils.isEmpty(login))
            view.showLoginError();

        if (TextUtils.isEmpty(password))
            view.showPasswordError();


        if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password))
            return;

        subscription.add(repository.auth(login, password)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe(authorization -> view.openRepositoryScreen(),
                        throwable -> view.showAuthError()));
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
