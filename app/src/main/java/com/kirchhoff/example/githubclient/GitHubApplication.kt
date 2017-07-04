package com.kirchhoff.example.githubclient

import android.app.Application

import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.HawkBuilder
import com.orhanobut.hawk.LogLevel

/**
 * @author Kirchhoff-
 */

class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(this))
                .setLogLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)
                .build()
    }
}
