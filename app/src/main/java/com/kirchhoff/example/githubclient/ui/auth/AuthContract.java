package com.kirchhoff.example.githubclient.ui.auth;

import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.ui.BasePresenter;
import com.kirchhoff.example.githubclient.ui.BaseView;

/**
 * @author Kirchhoff-
 */

public interface AuthContract {


    interface View extends BaseView {

        void openRepositoryScreen();

        void showLoginError();

        void showPasswordError();

    }

    interface Presenter extends BasePresenter {

        void auth(@Nullable String login, @Nullable String password);

    }
}
