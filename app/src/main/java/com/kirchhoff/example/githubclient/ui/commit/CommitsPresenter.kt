package com.kirchhoff.example.githubclient.ui.commit

import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.repository.GitHubDataSource
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider
import rx.subscriptions.CompositeSubscription

/**
 * @author Kirchhoff-
 */
class CommitsPresenter : CommitsContract.Presenter {

    private val repository: GitHubDataSource

    private val view: CommitsContract.View

    private val schedulerProvider: BaseSchedulerProvider

    private val subscription: CompositeSubscription


    constructor(repository: GitHubDataSource,
                view: CommitsContract.View,
                schedulerProvider: BaseSchedulerProvider) {
        this.repository = repository
        this.view = view
        this.schedulerProvider = schedulerProvider
        this.subscription = CompositeSubscription()
    }

    override fun loadCommits(repos: String) {
        subscription.add(repository.getCommits(repos)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .subscribe({ commitResponse: List<CommitResponse>? ->
                    if (commitResponse == null || commitResponse.isEmpty())
                        view.showEmptyView()
                    else
                        view.showCommits(commitResponse)
                }, { view.showError() }))
    }

    override fun unsubscribe() {
        subscription.clear()
    }
}