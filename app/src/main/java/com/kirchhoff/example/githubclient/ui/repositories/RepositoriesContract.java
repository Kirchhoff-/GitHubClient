package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.Repository;

import java.util.List;

/**
 * @author Kirchhoff-
 */

public interface RepositoriesContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showRepositories(@NonNull List<Repository> repository);

        void openRepository(@NonNull Repository repository);

        void showEmptyView();

        void showError();
    }

    interface Presenter {

        void loadRepositoriesList();

        void onRepositoryClick(@NonNull Repository repository);

        void unsubscribe();
    }
}
