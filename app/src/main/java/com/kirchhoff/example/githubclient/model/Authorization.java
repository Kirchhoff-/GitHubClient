package com.kirchhoff.example.githubclient.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Kirchhoff-
 */

public class Authorization {

    @SerializedName("id")
    private int id;

    @SerializedName("token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
