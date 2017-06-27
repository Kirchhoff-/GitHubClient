package com.kirchhoff.example.githubclient.utils;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirchhoff-
 */

public abstract class BaseRecyclerAdapter<VH extends RecyclerView.ViewHolder, T> extends
        RecyclerView.Adapter<VH> {

    private final List<T> items = new ArrayList<>();

    @Nullable
    private OnItemClickListener<T> onItemClickListener;

    private final View.OnClickListener clickListener =
            (view) -> {
                if (onItemClickListener != null) {
                    int position = (int) view.getTag();
                    T item = items.get(position);
                    onItemClickListener.onItemClick(item);
                }
            };

    public BaseRecyclerAdapter(@NonNull List<T> items) {
        this.items.addAll(items);
    }

    public final void add(@NonNull T value) {
        items.add(value);
        notifyDataSetChanged();
    }

    public final void changeDataSet(@NonNull List<T> values) {
        items.clear();
        items.addAll(values);
        notifyDataSetChanged();
    }

    public final void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @CallSuper
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(clickListener);
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener<T> {
        void onItemClick(@NonNull T item);
    }
}
