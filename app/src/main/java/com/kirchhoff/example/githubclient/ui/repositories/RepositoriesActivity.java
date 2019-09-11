package com.kirchhoff.example.githubclient.ui.repositories;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kirchhoff.example.githubclient.Injection;
import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.ui.auth.AuthActivity;
import com.kirchhoff.example.githubclient.ui.commit.CommitsActivity;
import com.kirchhoff.example.githubclient.ui.general.ScrollChildSwipeRefreshLayout;
import com.kirchhoff.example.githubclient.utils.BaseRecyclerAdapter;

import java.util.List;

public class RepositoriesActivity extends AppCompatActivity implements RepositoriesContract.View, BaseRecyclerAdapter.OnItemClickListener<Repository> {

    private RecyclerView recyclerView;
    private TextView emptyTextView;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        emptyTextView = (TextView) findViewById(R.id.emptyTextView);
        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        presenter = new RepositoriesPresenter(Injection.provideGitHubRepository(),
                Injection.provideKeyValueStorage(),
                this,
                Injection.provideSchedulerProvider());

        presenter.loadRepositoriesList();

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadRepositoriesList());
        swipeRefreshLayout.setScrollUpChild(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repository_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            presenter.logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void showRepositories(@NonNull List<Repository> repository) {
        if (adapter == null) {
            adapter = new RepositoriesAdapter(repository);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        } else {
            adapter.changeDataSet(repository);
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

        Toast.makeText(this, R.string.repositories_error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    public void onItemClick(@NonNull Repository item) {
        presenter.onRepositoryClick(item);
    }

    @Override
    public void openRepository(@NonNull Repository repository) {
        CommitsActivity.start(this, repository);
    }

    @Override
    public void moveToAuth() {
        AuthActivity.start(this);
        finish();
    }
}
