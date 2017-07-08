package com.kirchhoff.example.githubclient.ui.repositories

import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.ui.BasePresenter
import com.kirchhoff.example.githubclient.ui.BaseView

/**
 * @author Kirchhoff-
 */
interface RepositoriesContract {

    interface View : BaseView {

        fun showRepositories(repositoryList: List<Repository>)

        fun openRepository(repository: Repository)

        fun moveToAuth()

        fun showEmptyView()
    }


    interface Presenter : BasePresenter {

        fun loadRepositoriesList()

        fun onRepositoryClick(repository: Repository)

        fun logout()
    }


}