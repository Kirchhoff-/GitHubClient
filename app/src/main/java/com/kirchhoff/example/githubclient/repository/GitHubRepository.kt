package com.kirchhoff.example.githubclient.repository

import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.api.ApiFactory
import com.kirchhoff.example.githubclient.model.Authorization
import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.utils.AuthorizationUtils
import rx.Observable

/**
 * @author Kirchhoff-
 */
object GitHubRepository : GitHubDataSource {

    override fun auth(login: String, password: String): Observable<Authorization> {
        val authorizationString = AuthorizationUtils.createAuthorizationString(login, password)

        return ApiFactory.getGitHubService().authorize(authorizationString,
                AuthorizationUtils.createAuthorizationParam()).doOnNext { ApiFactory.recreate() }
    }

    override fun repositories(): Observable<List<Repository>> =
            ApiFactory.getGitHubService().repositories()

    override fun getCommits(repos: String): Observable<List<CommitResponse>> =
            ApiFactory.getGitHubService().commits(Injection.provideKeyValueStorage().userName, repos)

    override fun logout() {
        ApiFactory.recreate()
    }

}