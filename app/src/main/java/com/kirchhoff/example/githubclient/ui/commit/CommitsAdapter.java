package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.CommitResponse;
import com.kirchhoff.example.githubclient.utils.BaseRecyclerAdapter;

import java.util.List;

public final class CommitsAdapter extends BaseRecyclerAdapter<CommitsViewHolder, CommitResponse> {

    CommitsAdapter(@NonNull List<CommitResponse> items) {
        super(items);
    }

    @Override
    public CommitsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommitsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commits_row, parent, false));
    }

    @Override
    public void onBindViewHolder(CommitsViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        CommitResponse commit = getItem(position);
        holder.bind(commit);
    }
}
