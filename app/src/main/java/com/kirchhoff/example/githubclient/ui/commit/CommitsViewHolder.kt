package com.kirchhoff.example.githubclient.ui.commit

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kirchhoff.example.githubclient.model.CommitResponse
import kotlinx.android.synthetic.main.commits_row.view.*

/**
 * @author Kirchhoff-
 */
class CommitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(commit: CommitResponse) {
        with(commit) {
            itemView.commitAuthor.text = commit.commit.author.authorName
            itemView.commitMessage.text = commit.commit.message
        }
    }
}