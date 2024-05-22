package com.fit2081.assignment3.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.assignment3.Adapter.CategoryAdapter;
import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.R;

import java.util.List;


public class ListCategoryActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;
    private CategoryAdapter categoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view_categories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Initialize the adapter and set it to the RecyclerView
        categoryAdapter = new CategoryAdapter();
        recyclerView.setAdapter(categoryAdapter);

        // Initialize the ViewModel
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        // Observe the LiveData from ViewModel
        eventViewModel.getAllCategories().observe(this, new Observer<List<EventCategory>>() {
            @Override
            public void onChanged(List<EventCategory> eventCategories) {
                categoryAdapter.setCategories(eventCategories);
                if (eventCategories != null && !eventCategories.isEmpty()) {
                    categoryAdapter.setCategories(eventCategories);
                    Log.d("Activity", eventCategories.toString());
                }
            }
        });

    }
}
