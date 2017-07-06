package com.kirchhoff.example.githubclient.api.interceptor

import com.kirchhoff.example.githubclient.Injection
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @author Kirchhoff-
 */

class AuthorizationInterceptor private constructor() : Interceptor {

    private val token: String = Injection.provideKeyValueStorage().token

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (token.isEmpty()) {
            return chain.proceed(chain.request())
        }

        val request = chain.request().newBuilder()
                .addHeader("Authorization", String.format("%s %s", "token", token)).build()
        return chain.proceed(request)
    }

    companion object {

        fun create(): Interceptor {
            return AuthorizationInterceptor()
        }
    }
}