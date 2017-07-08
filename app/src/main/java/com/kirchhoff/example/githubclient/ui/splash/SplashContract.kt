package com.kirchhoff.example.githubclient.ui.splash

/**
 * @author Kirchhoff-
 */
interface SplashContract {

    interface View {

        fun openAuthScreen()

        fun openRepositoriesScreen()
    }

    interface Presenter {

        fun subscribe()

    }
}