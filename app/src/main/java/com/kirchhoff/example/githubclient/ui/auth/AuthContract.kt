package com.kirchhoff.example.githubclient.ui.auth

import com.kirchhoff.example.githubclient.ui.BasePresenter
import com.kirchhoff.example.githubclient.ui.BaseView

/**
 * @author Kirchhoff-
 */
interface AuthContract {

    interface View : BaseView {

        fun openRepositoryScreen()

        fun showLoginError()

        fun showPasswordError()
    }

    interface Presenter : BasePresenter {

        fun auth(login: String, password: String)
    }
}