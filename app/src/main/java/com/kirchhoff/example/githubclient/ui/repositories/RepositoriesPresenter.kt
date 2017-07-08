package com.kirchhoff.example.githubclient.ui.repositories

import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.repository.GitHubDataSource
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider
import rx.subscriptions.CompositeSubscription

/**
 * @author Kirchhoff-
 */
class RepositoriesPresenter : RepositoriesContract.Presenter {


    private val repository: GitHubDataSource

    private val view: RepositoriesContract.View

    private val schedulerProvider: BaseSchedulerProvider

    private val storage: KeyValueStorage

    private val subscriptions: CompositeSubscription

    constructor(repository: GitHubDataSource,
                view: RepositoriesContract.View,
                schedulerProvider: BaseSchedulerProvider,
                storage: KeyValueStorage) {
        this.repository = repository
        this.view = view
        this.schedulerProvider = schedulerProvider
        this.storage = storage
        this.subscriptions = CompositeSubscription()
    }

    override fun loadRepositoriesList() {
        subscriptions.add(repository.repositories()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe({ repositories: List<Repository>? ->
                    if (repositories == null || repositories.isEmpty())
                        view.showEmptyView()
                    else
                        view.showRepositories(repositories)
                }, { view.showError() }))
    }

    override fun onRepositoryClick(repository: Repository) {
        view.openRepository(repository)
    }

    override fun logout() {
        storage.clear()
        repository.logout()
        view.moveToAuth()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}