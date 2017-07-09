package com.kirchhoff.example.githubclient.ui.commit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.kirchhoff.example.githubclient.Injection
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.extensions.setVisible
import com.kirchhoff.example.githubclient.model.CommitResponse
import kotlinx.android.synthetic.main.a_commits.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class CommitsActivity : AppCompatActivity(), CommitsContract.View {

    private lateinit var presenter: CommitsPresenter

    private var adapter: CommitsAdapter? = null

    companion object {
        val REPOSITORY_ARG = "REPOSITORY_ARG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_commits)

        setSupportActionBar(toolbar)
        presenter = CommitsPresenter(Injection.provideGitHubRepository(),
                this, Injection.provideSchedulerProvider())

        val repositoryArg = intent.getStringExtra(REPOSITORY_ARG)
        title = repositoryArg

        presenter.loadCommits(repositoryArg)

        swipeRefresh.setOnRefreshListener { presenter.loadCommits(repositoryArg) }
        swipeRefresh.setScrollUpChild(recyclerView)
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
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

        toast(R.string.commits_error)
    }

    override fun showCommits(commitList: List<CommitResponse>) {
        if (adapter == null) {
            adapter = CommitsAdapter(commitList)
            recyclerView.setVisible(true)
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        } else {
            adapter?.changeDataSet(commitList)
        }

        emptyTextView.setVisible(false)
        recyclerView.setVisible(true)
        swipeRefresh.setVisible(true)
    }

    override fun showEmptyView() {
        emptyTextView.setVisible(true)
        recyclerView.setVisible(false)
        swipeRefresh.setVisible(false)
    }
}