package com.kirchhoff.example.githubclient.api.interceptor;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.GitHubApplication;
import com.kirchhoff.example.githubclient.utils.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Kirchhoff-
 */

public class AuthorizationInterceptor implements Interceptor {

    private final String token;

    private AuthorizationInterceptor() {
        token = GitHubApplication.getAppComponent().keyValueStorage().getToken();
    }

    @NonNull
    public static Interceptor create() {
        return new AuthorizationInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (TextUtils.isEmpty(token)) {
            return chain.proceed(chain.request());
        }

        Request request = chain.request().newBuilder()
                .addHeader("Authorization", String.format("%s %s", "token", token)).build();
        return chain.proceed(request);
    }
}
