package com.kirchhoff.example.githubclient.ui.splash;

/**
 * @author Kirchhoff-
 */

public interface SplashContract {

    interface View {

        void openAuthScreen();

        void openRepositoriesScreen();

    }

    interface Presenter {

        void subscribe();

    }
}
