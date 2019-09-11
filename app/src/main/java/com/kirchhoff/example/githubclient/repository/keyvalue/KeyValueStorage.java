package com.kirchhoff.example.githubclient.repository.keyvalue;

import android.support.annotation.NonNull;

public interface KeyValueStorage {

    String TOKEN_KEY = "TOKEN_KEY";
    String USER_NAME_KEY = "USER_NAME_KEY";

    void saveToken(@NonNull String token);

    @NonNull
    String getToken();

    void saveUserName(@NonNull String userName);

    @NonNull
    String getUserName();

    void clear();
}
