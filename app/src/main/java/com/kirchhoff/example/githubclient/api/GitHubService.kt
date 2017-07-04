package com.kirchhoff.example.githubclient.api

import com.google.gson.JsonObject
import com.kirchhoff.example.githubclient.model.Authorization
import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.model.Repository
import retrofit2.http.*
import rx.Observable

/**
 * @author Kirchhoff-
 */

interface GitHubService {

    @POST("/authorizations")
    fun authorize(@Header("Authorization") authorization: String,
                  @Body params: JsonObject): Observable<Authorization>

    @GET("/user/repos")
    fun repositories(): Observable<List<Repository>>

    @GET("/repos/{user}/{repo}/commits")
    fun commits(@Path("user") user: String, @Path("repo") repo: String)
            : Observable<List<CommitResponse>>
}
