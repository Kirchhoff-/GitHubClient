package com.kirchhoff.example.githubclient.ui.repositories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kirchhoff.example.githubclient.model.Repository
import kotlinx.android.synthetic.main.repository_row.view.*

/**
 * @author Kirchhoff-
 */

class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(repository: Repository) {
        with(repository) {
            itemView.repositoryName.text = name
            itemView.repositoryDescription.text = description
            itemView.repositoryLanguage.text = language
            itemView.starCount.text = starsCount.toString()
            itemView.forkCount.text = forksCount.toString()
            itemView.watchersCount.text = watchersCount.toString()
        }
    }
}