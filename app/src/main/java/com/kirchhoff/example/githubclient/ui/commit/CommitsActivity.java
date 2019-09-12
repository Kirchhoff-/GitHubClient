package com.kirchhoff.example.githubclient.ui.commit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.ui.general.ScrollChildSwipeRefreshLayout;

import java.util.List;

public final class CommitsActivity extends AppCompatActivity implements CommitsContract.View {

    public final static String REPOSITORY_ARG = "REPOSITORY_ARG";

    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    CommitsAdapter adapter;

    CommitsPresenter presenter;

    private String repositoryArg;

    public static void start(Context context, Repository repository) {
        Intent intent = new Intent(context, CommitsActivity.class);
        intent.putExtra(REPOSITORY_ARG, repository.getName());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_commits);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        repositoryArg = getIntent().getStringExtra(REPOSITORY_ARG);
        setTitle(repositoryArg);

        presenter = new CommitsPresenter(Injection.provideGitHubRepository(), this,
                Injection.provideSchedulerProvider());

        presenter.loadCommits(repositoryArg);

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadCommits(repositoryArg));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
    }

    @Override
    public void showLoading() {
        emptyTextView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void openCommits(@NonNull List<CommitResponse> commitList) {
        if (adapter == null) {
            adapter = new CommitsAdapter(commitList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.changeDataSet(commitList);
        }

        emptyTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {
        emptyTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(this, R.string.commits_error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
