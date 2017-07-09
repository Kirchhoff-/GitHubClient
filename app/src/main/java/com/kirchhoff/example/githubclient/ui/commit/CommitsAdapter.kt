package com.kirchhoff.example.githubclient.ui.commit

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.model.CommitResponse
import com.kirchhoff.example.githubclient.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class CommitsAdapter(items: List<CommitResponse>)
    : BaseRecyclerAdapter<CommitsViewHolder, CommitResponse>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CommitsViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.commits_row, parent, false))

    override fun onBindViewHolder(holder: CommitsViewHolder, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
        val commit = getItem(position)
        holder.bind(commit)
    }
}