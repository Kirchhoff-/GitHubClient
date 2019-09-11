package com.kirchhoff.example.githubclient.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_splash);

        presenter = new SplashPresenter(Injection.provideKeyValueStorage(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
    }

    @Override
    public void openAuthScreen() {
        AuthActivity.start(this);
        finish();
    }

    @Override
    public void openRepositoriesScreen() {
        RepositoriesActivity.start(this);
        finish();
    }
}
