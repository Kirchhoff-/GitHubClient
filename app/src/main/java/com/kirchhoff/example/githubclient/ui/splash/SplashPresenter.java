package com.kirchhoff.example.githubclient.ui.splash;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.TextUtils;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * @author Kirchhoff-
 */

public class SplashPresenter implements SplashContract.Presenter {

    @NonNull
    private final KeyValueStorage storage;

    @NonNull
    private final SplashContract.View view;

    public SplashPresenter(@NonNull KeyValueStorage storage,
                           @NonNull SplashContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void subscribe() {
        Observable.just(true).delay(2, TimeUnit.SECONDS)
                .subscribe(aBoolean -> {
                    if (!TextUtils.isEmpty(storage.getUserName()))
                        view.openRepositoriesScreen();
                    else
                        view.openAuthScreen();
                });
    }
}
