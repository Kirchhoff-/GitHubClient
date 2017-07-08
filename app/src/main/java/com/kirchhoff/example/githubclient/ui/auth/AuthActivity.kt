package com.kirchhoff.example.githubclient.ui.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.extensions.getString
import com.kirchhoff.example.githubclient.ui.general.LoadingDialog
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity
import kotlinx.android.synthetic.main.a_auth.*
import org.jetbrains.anko.toast


class AuthActivity : AppCompatActivity(), AuthContract.View {

    private val presenter: AuthPresenter = AuthPresenter(Injection.provideGitHubRepository(),
            this, Injection.provideSchedulerProvider(),
            Injection.provideKeyValueStorage())

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_auth)

        enterButton.setOnClickListener {
            presenter.auth(loginEdit.getString(),
                    passwordEdit.getString())
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoading() {
        loadingDialog = LoadingDialog()
        loadingDialog?.show(supportFragmentManager, "TAG")
    }

    override fun hideLoading() {
        loadingDialog?.dismiss()
    }

    override fun showError() {
        toast(R.string.auth_error)
    }

    override fun openRepositoryScreen() {
        startActivity(Intent(this,
                RepositoriesActivity::class.java))
        finish()
    }

    override fun showLoginError() {
        loginInputLayout.error = getString(R.string.auth_login_error)
        loginInputLayout.isErrorEnabled = true
    }

    override fun showPasswordError() {
        passwordInputLayout.error = getString(R.string.auth_password_error)
        passwordInputLayout.isErrorEnabled = true
    }

}
