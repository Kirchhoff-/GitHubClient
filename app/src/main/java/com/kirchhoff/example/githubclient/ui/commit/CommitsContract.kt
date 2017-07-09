package com.kirchhoff.example.githubclient.ui.commit

import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.ui.BasePresenter
import com.kirchhoff.example.githubclient.ui.BaseView

/**
 * @author Kirchhoff-
 */
interface CommitsContract {

    interface View : BaseView {

        fun showCommits(commitList: List<CommitResponse>)

        fun showEmptyView()
    }

    interface Presenter : BasePresenter {

        fun loadCommits(repository: String)
    }
}