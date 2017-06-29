package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.repository.GitHubDataSource;
import com.kirchhoff.example.githubclient.repository.GitHubRepository;
import com.kirchhoff.example.githubclient.repository.keyvalue.HawkKeyValueStorage;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;
import com.kirchhoff.example.githubclient.utils.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Kirchhoff-
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    public GitHubDataSource provideGitHubRepository() {
        return new GitHubRepository();
    }

    @Provides
    @Singleton
    public BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }

    @Provides
    @Singleton
    public KeyValueStorage provideKeyValueStorage() {
        return new HawkKeyValueStorage();
    }
}