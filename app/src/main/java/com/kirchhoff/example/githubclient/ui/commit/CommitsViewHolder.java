package com.kirchhoff.example.githubclient.ui.commit;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kirchhoff.example.githubclient.R;
import com.kirchhoff.example.githubclient.model.Commit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Kirchhoff-
 */

public class CommitsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.commitAuthor)
    TextView commitAuthor;

    @BindView(R.id.commitMessage)
    TextView commitMessage;

    public CommitsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@NonNull Commit commit) {
        commitAuthor.setText(commit.getAuthor().getAuthorName());
        commitMessage.setText(commit.getMessage());
    }
}
