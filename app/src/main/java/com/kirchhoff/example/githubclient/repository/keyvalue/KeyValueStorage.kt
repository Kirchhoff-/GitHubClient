package com.kirchhoff.example.githubclient.repository.keyvalue


/**
 * @author Kirchhoff-
 */

interface KeyValueStorage {

    companion object {
        val TOKEN_KEY = "TOKEN_KEY"
        val USER_NAME_KEY = "USER_NAME_KEY"
    }

    fun saveToken(token: String)

    val token: String

    fun saveUserName(userName: String)

    val userName: String

    fun clear()
}
