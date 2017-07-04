package com.kirchhoff.example.githubclient.repository

/**
 * @author Kirchhoff-
 */


import com.kirchhoff.example.githubclient.model.Authorization
import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.model.Repository

import rx.Observable

/**
 * @author Kirchhoff-
 */

interface GitHubDataSource {

    fun auth(login: String, password: String): Observable<Authorization>

    fun repositories(): Observable<List<Repository>>

    fun getCommits(repos: String): Observable<List<CommitResponse>>

    fun logout()
}
