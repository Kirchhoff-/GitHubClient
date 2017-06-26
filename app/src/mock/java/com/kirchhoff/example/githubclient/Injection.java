package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.repository.FakeGitHubRepository;
import com.kirchhoff.example.githubclient.repository.FakeKeyValueStorage;
import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;
import com.kirchhoff.example.githubclient.utils.schedulers.ImmediateSchedulerProvider;

/**
 * @author Kirchhoff-
 *         This is useful for testing, since it allows us to use
 *         a fake instance of the class to isolate the dependencies and run a test hermetically.
 */

public class Injection {

    public static GitHubDataSource provideGitHubRepository() {
        return FakeGitHubRepository.getInstance();
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return new ImmediateSchedulerProvider();
    }

    public static KeyValueStorage provideKeyValueStorage() {
        return FakeKeyValueStorage.getInstance();
    }
}
