package com.kirchhoff.example.githubclient.ui.auth;

import android.support.annotation.Nullable;

/**
 * @author Kirchhoff-
 */

public interface AuthContract {


    interface View {

        void openRepositoryScreen();

        void showLoginError();

        void showPasswordError();

        void showLoading();

        void hideLoading();

        void showAuthError();

    }

    interface Presenter {

        void auth(@Nullable String login, @Nullable String password);

        void unsubscribe();
    }
}
