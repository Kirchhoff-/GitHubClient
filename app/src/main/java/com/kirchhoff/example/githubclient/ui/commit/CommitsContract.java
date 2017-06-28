package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.CommitResponse;

import java.util.List;

/**
 * @author Kirchhoff-
 */

public interface CommitsContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showCommits(@NonNull List<CommitResponse> commitList);

        void showEmptyView();

        void showError();
    }


    interface Presenter {

        void loadCommits(@NonNull String repository);

        void unsubscribe();
    }
}
