package com.kirchhoff.example.githubclient.api

import com.kirchhoff.example.githubclient.BuildConfig
import com.kirchhoff.example.githubclient.api.interceptor.AuthorizationInterceptor
import com.kirchhoff.example.githubclient.api.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Kirchhoff-
 */
object ApiFactory {

    private var service: GitHubService? = null
    fun getGitHubService(): GitHubService {
        if (service == null)
            service = buildRetrofit().create(GitHubService::class.java)

        return service!!
    }

    private fun buildRetrofit() =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

    private fun buildClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(AuthorizationInterceptor.create())
                    .addInterceptor(LoggingInterceptor.create())
                    .build()

    fun recreate() {
        service = null
    }
}