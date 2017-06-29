package com.kirchhoff.example.githubclient;

import com.kirchhoff.example.githubclient.repository.keyvalue.KeyValueStorage;
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity;
import com.kirchhoff.example.githubclient.ui.commit.CommitsActivity;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Kirchhoff-
 */

@Singleton
@Component(modules = {DataModule.class})
public interface AppComponent {

    void injectAuthActivity(AuthActivity authActivity);

    void injectRepositoriesActivity(RepositoriesActivity repositoriesActivity);

    void injectCommitsActivity(CommitsActivity commitsActivity);

    KeyValueStorage keyValueStorage();
}
