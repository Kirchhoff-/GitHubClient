package com.kirchhoff.example.githubclient.ui.repositories

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.extensions.setVisible
import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.a_repositories.*
import org.jetbrains.anko.toast


/**
 * @author Kirchhoff-
 */
class RepositoriesActivity : AppCompatActivity(), RepositoriesContract.View {

    private lateinit var presenter: RepositoriesPresenter
    private var adapter: RepositoriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_repositories)

        presenter = RepositoriesPresenter(Injection.provideGitHubRepository(),
                this, Injection.provideSchedulerProvider(), Injection.provideKeyValueStorage())

        presenter.loadRepositoriesList()

        swipeRefresh.setOnRefreshListener { presenter.loadRepositoriesList() }
        swipeRefresh.setScrollUpChild(recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.repository_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            presenter.logout()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        emptyTextView.setVisible(false)
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showError() {
        recyclerView.setVisible(false)
        emptyTextView.setVisible(false)
        swipeRefresh.isRefreshing = false

        toast(R.string.repositories_error)
    }

    override fun showRepositories(repositoryList: List<Repository>) {
        if (adapter == null) {
            adapter = RepositoriesAdapter(repositoryList)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        } else {
            adapter?.changeDataSet(repositoryList)
        }

        emptyTextView.setVisible(false)
        recyclerView.setVisible(true)
        swipeRefresh.setVisible(true)
    }

    override fun openRepository(repository: Repository) {
        //Empty for now
    }

    override fun moveToAuth() {
        startActivity(Intent(this,
                AuthActivity::class.java))
        finish()
    }

    override fun showEmptyView() {
        emptyTextView.setVisible(true)
        recyclerView.setVisible(false)
        swipeRefresh.setVisible(false)
    }
}