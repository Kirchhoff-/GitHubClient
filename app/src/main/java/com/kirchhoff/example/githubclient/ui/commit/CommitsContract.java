package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.ui.BasePresenter;
import com.kirchhoff.example.githubclient.ui.BaseView;

import java.util.List;

public interface CommitsContract {

    interface View extends BaseView {

        void openCommits(@NonNull List<CommitResponse> commitList);

        void showEmptyView();
    }


    interface Presenter extends BasePresenter {

        void loadCommits(@NonNull String repository);

    }
}
