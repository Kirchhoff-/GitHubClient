package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.ui.BasePresenter;
import com.kirchhoff.example.githubclient.ui.BaseView;

import java.util.List;

/**
 * @author Kirchhoff-
 */

public interface RepositoriesContract {

    interface View extends BaseView {

        void showRepositories(@NonNull List<Repository> repository);

        void openRepository(@NonNull Repository repository);

        void moveToAuth();

        void showEmptyView();
    }

    interface Presenter extends BasePresenter {

        void loadRepositoriesList();

        void onRepositoryClick(@NonNull Repository repository);

        void logout();
    }
}
