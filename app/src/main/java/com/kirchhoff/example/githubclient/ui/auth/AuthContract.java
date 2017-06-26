package com.kirchhoff.example.githubclient.ui.auth;

import android.support.annotation.NonNull;

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

    }

    interface Presenter {

        void auth(@NonNull String login, @NonNull String password);
    }
}
