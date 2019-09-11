package com.kirchhoff.example.githubclient.ui.repositories;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Repository;
import com.kirchhoff.example.githubclient.utils.BaseRecyclerAdapter;

import java.util.List;

public class RepositoriesAdapter extends BaseRecyclerAdapter<RepositoriesViewHolder, Repository> {

    RepositoriesAdapter(@NonNull List<Repository> items) {
        super(items);
    }

    @Override
    public RepositoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_row, parent, false));
    }

    @Override
    public void onBindViewHolder(RepositoriesViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Repository repository = getItem(position);
        holder.bind(repository);
    }
}
