package com.kirchhoff.example.githubclient.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.ui.general.LoadingDialog;
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {

    private TextInputLayout loginInputLayout;
    private TextInputEditText loginEdit;
    private TextInputLayout passwordInputLayout;
    private TextInputEditText passwordEdit;

    private LoadingDialog loadingDialog;

    private AuthPresenter presenter;

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, AuthActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_auth);

        loginInputLayout = (TextInputLayout) findViewById(R.id.loginInputLayout);
        loginEdit = (TextInputEditText) findViewById(R.id.loginEdit);
        passwordInputLayout = (TextInputLayout) findViewById(R.id.passwordInputLayout);
        passwordEdit = (TextInputEditText) findViewById(R.id.passwordEdit);
        findViewById(R.id.enterButton).setOnClickListener(v -> presenter.auth(loginEdit.getText().toString(),
                passwordEdit.getText().toString()));

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        presenter = new AuthPresenter(Injection.provideGitHubRepository(),
                this,
                Injection.provideSchedulerProvider(),
                Injection.provideKeyValueStorage());
    }

    @Override
    public void openRepositoryScreen() {
        RepositoriesActivity.start(this);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void showLoginError() {
        loginInputLayout.setError(getString(R.string.auth_login_error));
        loginInputLayout.setErrorEnabled(true);
    }

    @Override
    public void showPasswordError() {
        passwordInputLayout.setError(getString(R.string.auth_password_error));
        passwordInputLayout.setErrorEnabled(true);
    }

    @Override
    public void showLoading() {
        loadingDialog = new LoadingDialog();
        loadingDialog.show(getSupportFragmentManager(), "TAG");
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null)
            loadingDialog.dismiss();
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.auth_error, Toast.LENGTH_LONG).show();
    }
}
