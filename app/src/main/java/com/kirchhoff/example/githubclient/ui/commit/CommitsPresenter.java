package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Kirchhoff-
 */

public class CommitsPresenter implements CommitsContract.Presenter {

    @NonNull
    private final GitHubDataSource repository;

    @NonNull
    private final CommitsContract.View view;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    public CommitsPresenter(@NonNull GitHubDataSource repository,
                            @NonNull CommitsContract.View view,
                            @NonNull BaseSchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.subscription = new CompositeSubscription();
    }


    @Override
    public void loadCommits(@NonNull String repos) {
        subscription.add(repository.getCommits(repos)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe(commitResponses -> {
                    if (commitResponses == null || commitResponses.isEmpty()) {
                        view.showEmptyView();
                    } else {
                        view.showCommits(commitResponses);
                    }
                }, throwable -> view.showError()));
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
