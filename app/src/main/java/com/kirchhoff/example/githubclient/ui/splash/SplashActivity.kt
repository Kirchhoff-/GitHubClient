package com.kirchhoff.example.githubclient.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity
import com.kirchhoff.example.githubclient.ui.repositories.RepositoriesActivity

/**
 * @author Kirchhoff-
 */
class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_splash)

        presenter = SplashPresenter(Injection.provideKeyValueStorage(), this)
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun openAuthScreen() {
        startActivity(Intent(this,
                AuthActivity::class.java))
        finish()
    }

    override fun openRepositoriesScreen() {
        startActivity(Intent(this,
                RepositoriesActivity::class.java))
        finish()
    }
}