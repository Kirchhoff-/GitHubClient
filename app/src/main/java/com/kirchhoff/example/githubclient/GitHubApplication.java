package com.kirchhoff.example.githubclient;

import android.app.Application;
import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

/**
 * @author Kirchhoff-
 */

public class GitHubApplication extends Application {

    private static AppComponent appComponent;

    @NonNull
    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(BuildConfig.DEBUG ? LogLevel.FULL : LogLevel.NONE)
                .build();

        appComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .build();
    }
}
