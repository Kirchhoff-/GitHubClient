package com.kirchhoff.example.githubclient.ui.splash

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Kirchhoff-
 */
class SplashPresenter : SplashContract.Presenter {

    private val storage: KeyValueStorage

    private val view: SplashContract.View

    constructor(storage: KeyValueStorage,
                view: SplashContract.View) {
        this.storage = storage
        this.view = view
    }

    override fun subscribe() {
        Observable.just(true).delay(2, TimeUnit.SECONDS)
                .subscribe {
                    if (storage.userName.isNullOrEmpty())
                        view.openAuthScreen()
                    else
                        view.openRepositoriesScreen()
                }
    }
}