package com.kirchhoff.example.githubclient.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;

/**
 * @author Kirchhoff-
 */

public class FakeKeyValueStorage implements KeyValueStorage {

    @Nullable
    private static FakeKeyValueStorage INSTANCE = null;
    private String userName;

    private FakeKeyValueStorage() {
    }

    public static FakeKeyValueStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeKeyValueStorage();
        }

        return INSTANCE;
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
    public void savePassedKey() {

    }

    @Override
    public boolean isPassedKeyExist() {
        return false;
    }

    @Override
    public void clear() {

    }
}
