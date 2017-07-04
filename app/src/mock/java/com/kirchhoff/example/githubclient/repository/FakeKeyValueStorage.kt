package com.kirchhoff.example.githubclient.repository

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage

/**
 * @author Kirchhoff-
 */
object FakeKeyValueStorage : KeyValueStorage {

    private var fakeUserName = ""

    override fun saveToken(token: String) {
        //Empty
    }

    override val token: String
        get() = ""

    override fun saveUserName(userName: String) {
        fakeUserName = userName
    }

    override val userName: String
        get() = fakeUserName

    override fun clear() {
        //Empty
    }

}