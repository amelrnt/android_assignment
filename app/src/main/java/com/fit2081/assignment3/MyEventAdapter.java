package com.fit2081.assignment3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Data.Event;

import java.util.List;

public class MyEventAdapter extends RecyclerView.Adapter<MyEventAdapter.EventViewHolder> {

    private final List<Event> eventList;

    public MyEventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dashboard, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventId.setText(event.getId());
        holder.categoryId.setText(event.getCategoryId());
        holder.eventName.setText(event.getName());
        holder.ticketsAvailable.setText(String.valueOf(event.getTicketQty()));
        holder.isActive.setText(event.getIsActive() ? "Yes" : "No");
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventId, categoryId, eventName, ticketsAvailable, isActive;

        EventViewHolder(View itemView) {
            super(itemView);
            eventId = itemView.findViewById(R.id.card_event_id);
            categoryId = itemView.findViewById(R.id.card_category_id);
            eventName = itemView.findViewById(R.id.card_event_name);
            ticketsAvailable = itemView.findViewById(R.id.card_event_tickets);
            isActive = itemView.findViewById(R.id.card_is_active);
        }
    }
}
