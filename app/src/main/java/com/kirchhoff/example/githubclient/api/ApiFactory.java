package com.kirchhoff.example.githubclient.api;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.BuildConfig;
import com.kirchhoff.example.githubclient.api.interceptor.AuthorizationInterceptor;
import com.kirchhoff.example.githubclient.api.interceptor.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Kirchhoff-
 */

public class ApiFactory {

    private static volatile GitHubService service;

    @NonNull
    public static GitHubService getGitHubService() {
        if (service == null) {
            service = buildRetrofit().create(GitHubService.class);
        }

        return service;
    }

    public static void recreate() {
        service = null;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(buildClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor.create())
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }

}
