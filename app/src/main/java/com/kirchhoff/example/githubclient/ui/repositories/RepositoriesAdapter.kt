package com.kirchhoff.example.githubclient.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kirchhoff.example.githubclient.R
import com.kirchhoff.example.githubclient.model.Repository
import com.kirchhoff.example.githubclient.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */

class RepositoriesAdapter(items: List<Repository>)
    : BaseRecyclerAdapter<RepositoriesViewHolder, Repository>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RepositoriesViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.repository_row, parent, false))


    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val repository = getItem(position)
        holder.bind(repository)
    }

}