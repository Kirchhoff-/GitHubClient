package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.CommitResponse;

final class CommitsViewHolder extends RecyclerView.ViewHolder {

    private final TextView commitAuthor;
    private final TextView commitMessage;

    CommitsViewHolder(View itemView) {
        super(itemView);
        commitAuthor = (TextView) itemView.findViewById(R.id.commitAuthor);
        commitMessage = (TextView) itemView.findViewById(R.id.commitMessage);
    }

    void bind(@NonNull CommitResponse commit) {
        commitAuthor.setText(commit.getCommit().getAuthor().getAuthorName());
        commitMessage.setText(commit.getCommit().getMessage());
    }
}
