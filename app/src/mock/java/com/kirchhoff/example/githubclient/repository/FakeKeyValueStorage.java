package com.kirchhoff.example.githubclient.repository;

import android.support.annotation.NonNull;

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;

/**
 * @author Kirchhoff-
 */

public class FakeKeyValueStorage implements KeyValueStorage {

    private String userName;

    public FakeKeyValueStorage() {
    }


    @Override
    public void saveToken(@NonNull String token) {

    }

    @NonNull
    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void saveUserName(@NonNull String userName) {
        this.userName = userName;
    }

    @NonNull
    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void clear() {

    }
}
