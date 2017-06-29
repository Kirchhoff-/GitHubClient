package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;

import rx.subscriptions.CompositeSubscription;

/**
 * @author Kirchhoff-
 */

public class RepositoriesPresenter implements RepositoriesContract.Presenter {

    @NonNull
    private final GitHubDataSource repository;

    @NonNull
    private final RepositoriesContract.View view;

    @NonNull
    private final KeyValueStorage storage;

    @NonNull
    private final BaseSchedulerProvider schedulerProvider;

    @NonNull
    private CompositeSubscription subscription;

    public RepositoriesPresenter(@NonNull GitHubDataSource repository,
                                 @NonNull KeyValueStorage storage,
                                 @NonNull RepositoriesContract.View view,
                                 @NonNull BaseSchedulerProvider schedulerProvider) {
        this.repository = repository;
        this.storage = storage;
        this.view = view;
        this.schedulerProvider = schedulerProvider;
        this.subscription = new CompositeSubscription();
    }

    @Override
    public void loadRepositoriesList() {

        subscription.add(repository.repositories()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe(repositories -> {
                            if (repositories == null || repositories.isEmpty())
                                view.showEmptyView();
                            else
                                view.showRepositories(repositories);
                        },
                        throwable -> view.showError()));
    }

    @Override
    public void onRepositoryClick(@NonNull Repository repository) {
        view.openRepository(repository);
    }

    @Override
    public void logout() {
        storage.clear();
        repository.logout();
        view.moveToAuth();
    }

    @Override
    public void unsubscribe() {
        subscription.clear();
    }
}
