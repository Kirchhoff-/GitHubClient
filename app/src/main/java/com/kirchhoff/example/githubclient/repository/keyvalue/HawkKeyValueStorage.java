package com.kirchhoff.example.githubclient.repository.keyvalue;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.hawk.Hawk;

public class HawkKeyValueStorage implements KeyValueStorage {

    @Nullable
    private static HawkKeyValueStorage INSTANCE = null;

    private HawkKeyValueStorage() {
    }

    public static HawkKeyValueStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HawkKeyValueStorage();
        }

        return INSTANCE;
    }

    @Override
    public void saveToken(@NonNull String token) {
        Hawk.put(TOKEN_KEY, token);
    }

    @NonNull
    @Override
    public String getToken() {
        return Hawk.get(TOKEN_KEY, "");
    }

    @Override
    public void saveUserName(@NonNull String userName) {
        Hawk.put(USER_NAME_KEY, userName);
    }

    @NonNull
    @Override
    public String getUserName() {
        return Hawk.get(USER_NAME_KEY, "");
    }

    @Override
    public void clear() {
        Hawk.clear();
    }
}
