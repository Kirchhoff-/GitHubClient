package com.kirchhoff.example.githubclient.repository.keyvalue


import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage.Companion.TOKEN_KEY
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage.Companion.USER_NAME_KEY
import com.orhanobut.hawk.Hawk

/**
 * @author Kirchhoff-
 */

object HawkKeyValueStorage : KeyValueStorage {

    override fun saveToken(token: String) {
        Hawk.put(TOKEN_KEY, token)
    }

    override val token: String
        get() = Hawk.get(TOKEN_KEY, "")

    override fun saveUserName(userName: String) {
        Hawk.put(USER_NAME_KEY, userName)
    }

    override val userName: String
        get() = Hawk.get(USER_NAME_KEY, "")

    override fun clear() {
        Hawk.clear()
    }

}
