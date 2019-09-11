package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Repository;

class RepositoriesViewHolder extends RecyclerView.ViewHolder {

    private final TextView repositoryName;
    private final TextView repositoryDescription;
    private final TextView repositoryLanguage;
    private final TextView starCount;
    private final TextView forkCount;
    private final TextView watchersCount;

    RepositoriesViewHolder(View itemView) {
        super(itemView);
        repositoryName = (TextView) itemView.findViewById(R.id.repositoryName);
        repositoryDescription = (TextView) itemView.findViewById(R.id.repositoryDescription);
        repositoryLanguage = (TextView) itemView.findViewById(R.id.repositoryLanguage);
        starCount = (TextView) itemView.findViewById(R.id.starCount);
        forkCount = (TextView) itemView.findViewById(R.id.forkCount);
        watchersCount = (TextView) itemView.findViewById(R.id.watchersCount);
    }

    void bind(@NonNull Repository repository) {
        repositoryName.setText(repository.getName());
        repositoryDescription.setText(repository.getDescription());
        repositoryLanguage.setText(repository.getLanguage());
        starCount.setText(String.valueOf(repository.getStarsCount()));
        forkCount.setText(String.valueOf(repository.getForksCount()));
        watchersCount.setText(String.valueOf(repository.getWatchersCount()));
    }
}
