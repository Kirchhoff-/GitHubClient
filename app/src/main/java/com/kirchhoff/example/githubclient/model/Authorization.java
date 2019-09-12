package com.kirchhoff.example.githubclient.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public final class Authorization {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
