package com.kirchhoff.example.githubclient.utils

import android.support.annotation.CallSuper
import android.support.v7.widget.RecyclerView
import android.view.View
import java.util.*

/**
 * @author Kirchhoff-
 */

abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, T>(items: List<T>)
    : RecyclerView.Adapter<VH>() {

    private val items = ArrayList<T>()

    private var onItemClickListener: OnItemClickListener<T>? = null

    private val clickListener = View.OnClickListener { view ->
        val position: Int = view.tag as Int
        val item = items.get(position)
        onItemClickListener?.onItemClick(item)
    }

    init {
        this.items.addAll(items)
    }

    fun add(value: T) {
        items.add(value)
        notifyDataSetChanged()
    }

    fun changeDataSet(values: List<T>) {
        items.clear()
        items.addAll(values)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(clickListener)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>) {
        this.onItemClickListener = onItemClickListener
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }
}
