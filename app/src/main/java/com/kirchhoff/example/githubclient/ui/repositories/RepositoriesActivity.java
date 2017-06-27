package com.kirchhoff.example.githubclient.ui.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.ui.general.ScrollChildSwipeRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Kirchhoff-
 */

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.emptyTextView)
    TextView emptyTextView;

    @BindView(R.id.swipeRefresh)
    ScrollChildSwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    RepositoriesAdapter adapter;

    RepositoriesPresenter presenter;

    public static void start(Context context) {
        Intent intent = new Intent(context, RepositoriesActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_repositories);
        ButterKnife.bind(this);

        presenter = new RepositoriesPresenter(Injection.provideGitHubRepository(), this,
                Injection.provideSchedulerProvider());

        presenter.loadRepositoriesList();

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadRepositoriesList());
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
    public void showRepositories(@Nullable List<Repository> repository) {
        if (repository == null || repository.isEmpty()) {
            emptyTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {

            if (adapter == null) {
                adapter = new RepositoriesAdapter(repository);
                recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                recyclerView.setAdapter(adapter);
            } else {
                adapter.changeDataSet(repository);
            }

            emptyTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(this, R.string.repositories_error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }
}
