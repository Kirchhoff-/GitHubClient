package com.kirchhoff.example.githubclient.repository.keyvalue;

import android.support.annotation.NonNull;

/**
 * @author Kirchhoff-
 */

public interface KeyValueStorage {

    String TOKEN_KEY = "TOKEN_KEY";
    String USER_NAME_KEY = "USER_NAME_KEY";
    String PASSED_KEY = "PASSED_KEY";

    void saveToken(@NonNull String token);

    @NonNull
    String getToken();

    void saveUserName(@NonNull String userName);

    @NonNull
    String getUserName();

    void savePassedKey();

    boolean isPassedKeyExist();

    void clear();
}
