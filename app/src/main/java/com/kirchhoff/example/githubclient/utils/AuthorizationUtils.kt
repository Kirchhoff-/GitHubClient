package com.kirchhoff.example.githubclient.utils

import android.util.Base64

import com.google.gson.JsonObject
import com.kirchhoff.example.githubclient.BuildConfig

/**
 * @author Kirchhoff-
 */

object AuthorizationUtils {

    private val BASIC_AUTHORIZATION = "Basic "
    private val CLIENT_ID_PROPERTY = "client_id"
    private val CLIENT_SECRET_PROPERTY = "client_secret"

    fun createAuthorizationString(login: String,
                                  password: String): String {
        val combinedStr = String.format("%1\$s:%2\$s", login, password)
        val authorizationString = BASIC_AUTHORIZATION + Base64.encodeToString(combinedStr.toByteArray(),
                Base64.DEFAULT)

        return authorizationString.trim()
    }

    fun createAuthorizationParam(): JsonObject {
        val param = JsonObject()
        param.addProperty(CLIENT_ID_PROPERTY, BuildConfig.CLIENT_ID)
        param.addProperty(CLIENT_SECRET_PROPERTY, BuildConfig.CLIENT_SECRET)
        return param
    }

}
