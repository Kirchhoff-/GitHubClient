package com.kirchhoff.example.githubclient.ui.auth

import com.kirchhoff.example.githubclient.model.Authorization
import com.kirchhoff.example.githubclient.repository.GitHubDataSource
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider
import rx.Observable
import rx.subscriptions.CompositeSubscription


/**
 * @author Kirchhoff-
 */
class AuthPresenter : AuthContract.Presenter {


    private val repository: GitHubDataSource

    private val view: AuthContract.View

    private val schedulerProvider: BaseSchedulerProvider

    private val storage: KeyValueStorage

    private val subscriptions: CompositeSubscription

    constructor(repository: GitHubDataSource,
                view: AuthContract.View,
                schedulerProvider: BaseSchedulerProvider,
                storage: KeyValueStorage) {
        this.repository = repository
        this.view = view
        this.schedulerProvider = schedulerProvider
        this.storage = storage
        this.subscriptions = CompositeSubscription()
    }

    override fun auth(login: String, password: String) {

        if (login.isNullOrEmpty())
            view.showLoginError()

        if (password.isNullOrEmpty())
            view.showPasswordError()

        if (login.isNullOrEmpty() || password.isNullOrEmpty())
            return

        subscriptions.add(repository.auth(login, password)
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .flatMap { authorization: Authorization ->
                    storage.saveToken(authorization.token)
                    storage.saveUserName(login)
                    Observable.just(authorization)
                }.doOnError { storage.clear() }
                .subscribe({ view.openRepositoryScreen() }, { view.showError() }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}