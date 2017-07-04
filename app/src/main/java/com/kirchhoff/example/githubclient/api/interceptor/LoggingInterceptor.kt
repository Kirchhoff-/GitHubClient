package com.kirchhoff.example.githubclient.api.interceptor


import com.kirchhoff.example.githubclient.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

/**
 * @author Kirchhoff-
 */

class LoggingInterceptor private constructor() : Interceptor {

    private val loggingInterceptor: Interceptor

    init {
        loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return loggingInterceptor.intercept(chain)
    }

    companion object {

        fun create(): Interceptor {
            return LoggingInterceptor()
        }
    }
}
