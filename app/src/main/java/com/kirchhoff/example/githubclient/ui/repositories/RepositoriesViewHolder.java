package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Kirchhoff-
 */

public class RepositoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.repositoryName)
    TextView repositoryName;

    @BindView(R.id.repositoryDescription)
    TextView repositoryDescription;

    @BindView(R.id.repositoryLanguage)
    TextView repositoryLanguage;

    @BindView(R.id.starCount)
    TextView starCount;

    @BindView(R.id.forkCount)
    TextView forkCount;

    @BindView(R.id.watchersCount)
    TextView watchersCount;

    public RepositoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Repository repository) {
        repositoryName.setText(repository.getName());
        repositoryDescription.setText(repository.getDescription());
        repositoryLanguage.setText(repository.getLanguage());
        starCount.setText(String.valueOf(repository.getStarsCount()));
        forkCount.setText(String.valueOf(repository.getForksCount()));
        watchersCount.setText(String.valueOf(repository.getWatchersCount()));
    }
}
