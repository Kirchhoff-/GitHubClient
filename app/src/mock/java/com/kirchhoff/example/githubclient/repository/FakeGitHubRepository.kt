package com.kirchhoff.example.githubclient.repository

import com.kirchhoff.example.githubclient.Constants
import com.kirchhoff.example.githubclient.model.Authorization
import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.model.Repository
import rx.Observable
import java.io.IOException
import java.util.*

/**
 * @author Kirchhoff-
 */

object FakeGitHubRepository : GitHubDataSource {

    override fun auth(login: String, password: String): Observable<Authorization> {

        if (Constants.WRONG_LOGIN == login || Constants.WRONG_PASSWORD == password)
            return Observable.error<Authorization>(IOException())

        return Observable.just(Authorization(1, "token"))
    }

    override fun repositories(): Observable<List<Repository>> {

        if (Constants.DATA_TEST_ENUM == Constants.DataTestEnum.DATA) {
            return Observable.just(Constants.emulateRepositoryList())
        } else if (Constants.DATA_TEST_ENUM == Constants.DataTestEnum.EMPTY) {
            return Observable.just(ArrayList<Repository>())
        }


        return Observable.error<List<Repository>>(IOException())
    }

    override fun getCommits(repos: String): Observable<List<CommitResponse>> {
        if (Constants.DATA_TEST_ENUM == Constants.DataTestEnum.DATA) {
            return Observable.just(Constants.emulateCommitsList())
        } else if (Constants.DATA_TEST_ENUM == Constants.DataTestEnum.EMPTY) {
            return Observable.just(ArrayList<CommitResponse>())
        }

        return Observable.error<List<CommitResponse>>(IOException())
    }

    override fun logout() {
        //Empty
    }
}
