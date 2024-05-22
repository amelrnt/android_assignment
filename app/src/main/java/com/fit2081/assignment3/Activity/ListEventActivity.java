package com.fit2081.assignment3.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Adapter.EventAdapter;
import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.R;

import java.util.List;

public class ListEventActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize the adapter and set it to the RecyclerView
        eventAdapter = new EventAdapter();
        recyclerView.setAdapter(eventAdapter);

        // Initialize the ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Observe the LiveData from ViewModel
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> event) {
                if (event != null && !event.isEmpty()) {
                    eventAdapter.setEvent(event);
                    Log.d("Activity", event.toString());
                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button; navigate back
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
