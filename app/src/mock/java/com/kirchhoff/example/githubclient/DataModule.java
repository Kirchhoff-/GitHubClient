package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.repository.FakeGitHubRepository;
import com.kirchhoff.example.githubclient.repository.FakeKeyValueStorage;
import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;
import com.kirchhoff.example.githubclient.utils.schedulers.ImmediateSchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirchhoff-
 *         This is useful for testing, since it allows us to use
 *         a fake instance of the class to isolate the dependencies and run a test hermetically.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public GitHubDataSource provideGitHubRepository() {
        return new FakeGitHubRepository();
    }

    @Provides
    @Singleton
    public BaseSchedulerProvider provideSchedulerProvider() {
        return new ImmediateSchedulerProvider();
    }

    @Provides
    @Singleton
    public static KeyValueStorage provideKeyValueStorage() {
        return new  FakeKeyValueStorage();
    }
}
