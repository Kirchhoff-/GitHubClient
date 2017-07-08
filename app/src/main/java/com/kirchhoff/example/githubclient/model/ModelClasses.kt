package com.kirchhoff.example.githubclient.model

import com.google.gson.annotations.SerializedName

/**
 * @author Kirchhoff-
 */
data class Author(@SerializedName("name") val authorName: String)

data class Authorization(@SerializedName("id") val id: Int,
                         @SerializedName("token") val token: String)

data class Commit(val repoName: String,
                  @SerializedName("author") val author: Author,
                  @SerializedName("message") val message: String)

data class CommitResponse(@SerializedName("commit") val commit: Commit) {
    constructor() : this(Commit("repoName", Author("Author"), "message"))
}

data class Repository(@SerializedName("name") val name: String,
                      @SerializedName("description") val description: String,
                      @SerializedName("language") val language: String,
                      @SerializedName("stargazers_count") val starsCount: Int,
                      @SerializedName("forks_count") val forksCount: Int,
                      @SerializedName("watchers_count") val watchersCount: Int)