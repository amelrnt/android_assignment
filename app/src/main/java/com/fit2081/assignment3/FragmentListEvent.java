package com.fit2081.assignment3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class FragmentListEvent extends Fragment {

    private RecyclerView recyclerView;
    private MyEventAdapter eventAdapter;
    private List<Event> eventList = new ArrayList<>();
    private EventViewModel eventViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_event, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        eventAdapter = new MyEventAdapter(eventList);
        recyclerView.setAdapter(eventAdapter);

        loadCategoryFromRoom();

        return view;
    }

    private void loadCategoryFromRoom(){
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        // Observe data changes
        eventViewModel.getAllEvents().observe(getViewLifecycleOwner(), new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> event) {
                eventList = event;
            }
        });

        eventAdapter.notifyDataSetChanged();
    }

}