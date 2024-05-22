package com.fit2081.assignment3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Activity.GoogleSearchActivity;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> eventList = new ArrayList<>();
    private Context context;


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dashboard, parent, false);
        context = parent.getContext();
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        if (eventList.isEmpty()){
            Log.d("Activity", "Empty List");
            holder.eventId.setText("None");
            holder.categoryId.setText("None");
            holder.eventName.setText("None");
            holder.ticketsAvailable.setText("None");
            holder.isActive.setText("None");
        }
        else{
            Event currentEvent = eventList.get(position);
            holder.eventId.setText(currentEvent.getEventId());
            holder.categoryId.setText(currentEvent.getCategoryId());
            holder.eventName.setText(currentEvent.getName());
            holder.ticketsAvailable.setText(Integer.toString(currentEvent.getTicketQty()));
            holder.isActive.setText(currentEvent.getIsActive() ? "Yes" : "No");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, GoogleSearchActivity.class);
                    intent.putExtra("keyword_search", currentEvent.getName());
                    context.startActivity(intent);
                    Log.d("Activity", "item event on click");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void setEvent(List<Event> event) {
        this.eventList = event;
        notifyDataSetChanged();
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
