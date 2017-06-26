package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.repository.GitHubRepository;
import com.kirchhoff.example.githubclient.repository.keyvalue.HawkKeyValueStorage;
import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.utils.schedulers.BaseSchedulerProvider;
import com.kirchhoff.example.githubclient.utils.schedulers.SchedulerProvider;

/**
 * @author Kirchhoff-
 */

public class Injection {

    public static GitHubRepository provideGitHubRepository() {
        return GitHubRepository.getInstance();
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    public static KeyValueStorage provideKeyValueStorage() {
        return HawkKeyValueStorage.getInstance();
    }
}
