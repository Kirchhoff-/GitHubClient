package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.model.Repository;

import java.util.List;

/**
 * @author Kirchhoff-
 */

public interface RepositoriesContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showRepositories(@Nullable List<Repository> repository);

        void showError();
    }

    interface Presenter {

        void loadRepositoriesList();
    }
}
