package com.fit2081.assignment3.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventCategoryAdapter extends RecyclerView.Adapter<EventCategoryAdapter.EventCategoryViewHolder> {

    @NonNull
    @Override
    public EventCategoryAdapter.EventCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull EventCategoryAdapter.EventCategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class EventCategoryViewHolder extends RecyclerView.ViewHolder {
        public EventCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
