package com.fit2081.assignment3.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.FragmentListCategory;
import com.fit2081.assignment3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private List<Event> eventList = new ArrayList<>();
    private EditText editTextEventName;
    private EditText editTextCategoryId;
    private EditText editTextTicketsAvailable;
    private Switch switchIsActive;
    private String prevEventName;
    private String prevCategoryId;
    private String prevTicketsAvailable;
    private boolean prevIsActive;

    private EventViewModel eventViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout); // Make sure this layout is correct
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.drawer_menu);
//        navigationView.setNavigationItemSelectedListener(new MyNavigationListener(,drawerLayout));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentListCategory fragmentListCategory = new FragmentListCategory();
//        fragmentTransaction.replace(R.id.frameLayout, fragmentListCategory);
        fragmentTransaction.commit();

        // Initialize all your views
        editTextEventName = findViewById(R.id.editTextEventName2);
        editTextCategoryId = findViewById(R.id.editTextCategoryid2);
        editTextTicketsAvailable = findViewById(R.id.editTextTicketsAvailable2);
        switchIsActive = findViewById(R.id.is_active_toggle);

        // Assuming app_bar_layout includes a Toolbar with id toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize the FloatingActionButton
        FloatingActionButton fabSaveEvent = findViewById(R.id.fab);
        fabSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform the save operation
                saveEvent();

                // Show the Snackbar with UNDO action
                Snackbar.make(view, "Event saved", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Perform the undo operation
                                undoSaveEvent();
                            }
                        }).show();
            }
        });
    }

    private void undoSaveEvent() {
        // Restore the previous state from the member variables
        SharedPreferences sharedPreferences = getSharedPreferences("EventDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("eventName", prevEventName);
        editor.putString("categoryId", prevCategoryId);
        editor.putString("ticketsAvailable", prevTicketsAvailable);
        editor.putBoolean("isActive", prevIsActive);

        // Apply the undo
        editor.apply();

        // Clear the UI elements
        editTextEventName.setText(prevEventName);
        editTextCategoryId.setText(prevCategoryId);
        editTextTicketsAvailable.setText(prevTicketsAvailable);
        switchIsActive.setChecked(prevIsActive);

        // Show a message via Toast that the operation was undone
        Toast.makeText(this, "Undo successful", Toast.LENGTH_LONG).show();
    }



    private void saveEvent() {
        String eventName = editTextEventName.getText().toString();
        String categoryId = editTextCategoryId.getText().toString();
        String ticketsAvailableStr = editTextTicketsAvailable.getText().toString();
        boolean isActive = switchIsActive.isChecked();

        // Validate event name
        if (!validateEventName(eventName)) {
            Toast.makeText(this, "Invalid event name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate and correct tickets available
        int ticketsAvailable = validateAndCorrectTickets(ticketsAvailableStr);
        if (ticketsAvailable < 0) {
            return;
        }

        // Check if the category exists
        if (!categoryExists(categoryId)) {
            Toast.makeText(this, "Category does not exist", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the event details
        SharedPreferences sharedPreferences = getSharedPreferences("EventDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("eventName", eventName);
        editor.putString("categoryId", categoryId);
        editor.putString("ticketsAvailable", String.valueOf(ticketsAvailable));
        editor.putBoolean("isActive", isActive);
        editor.apply();

        // Update the event count for the category
        updateEventCount(categoryId);

        // Success message
        Toast.makeText(this, "Event saved: " + eventName + " to " + categoryId, Toast.LENGTH_LONG).show();
    }


    private boolean validateEventName(String eventName) {
        return !eventName.matches("\\d+") && !eventName.contains("%");
    }

    private boolean categoryExists(String categoryId) {
        SharedPreferences sharedPreferences = getSharedPreferences("CategoryDetails", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            if (entry.getKey().equals("category_id_" + categoryId)) {
                return true;
            }
        }
        return false;
    }
    private int validateAndCorrectTickets(String ticketsStr) {
        try {
            int tickets = Integer.parseInt(ticketsStr);
            return Math.max(tickets, 0); // Ensure non-negative
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid 'Tickets available'", Toast.LENGTH_SHORT).show();
            return -1; // Indicate an error with -1
        }
    }

    private void updateEventCount(String categoryId) {
        // Increment the event count for the given category
        SharedPreferences prefs = getSharedPreferences("CategoryCounts", MODE_PRIVATE);
        int currentCount = prefs.getInt(categoryId, 0);
        prefs.edit().putInt(categoryId, currentCount + 1).apply();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Handling ActionBarDrawerToggle
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (id == R.id.action_refresh) {
            refreshDashboard();
        } else if (id == R.id.action_clear_event_form) {
            clearEventForm();
        } else if (id == R.id.action_delete_all_categories) {
            deleteAllCategories();
        } else if (id == R.id.action_delete_all_events) {
            deleteAllEvents();
        }
        return true; // Indicate that the menu selection was handled
    }


    private void refreshDashboard() {
        finish();
        startActivity(getIntent());
    }

    private void clearEventForm() {
        // Clear the EditText for Event ID
        EditText editTextEventID = findViewById(R.id.editTextEventID2);
        if (editTextEventID != null) {
            editTextEventID.setText("");
        }

        // Clear the EditText for Event Name
        EditText editTextEventName = findViewById(R.id.editTextEventName2);
        if (editTextEventName != null) {
            editTextEventName.setText("");
        }

        // Clear the EditText for Category ID
        EditText editTextCategoryId = findViewById(R.id.editTextCategoryid2);
        if (editTextCategoryId != null) {
            editTextCategoryId.setText("");
        }

        // Clear the EditText for Tickets Available
        EditText editTextTicketsAvailable = findViewById(R.id.editTextTicketsAvailable2);
        if (editTextTicketsAvailable != null) {
            editTextTicketsAvailable.setText("");
        }

        // Reset the Switch for Is Active
        Switch switchIsActive = findViewById(R.id.is_active_toggle);
        if (switchIsActive != null) {
            switchIsActive.setChecked(false);
        }
    }


    private void deleteAllCategories() {
        eventViewModel.deleteAllCategories();
        Toast.makeText(DashboardActivity.this, "All Categories are deleted", Toast.LENGTH_SHORT).show();
    }

    private void deleteAllEvents() {
        eventViewModel.deleteAllEvents();
        Toast.makeText(DashboardActivity.this, "All Event are deleted", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    class MyNavigationListener implements NavigationView.OnNavigationItemSelectedListener {

        private final AppCompatActivity activity;
        private final DrawerLayout drawerLayout;

        // Constructor to initialize activity and drawerLayout
        public MyNavigationListener(AppCompatActivity activity, DrawerLayout drawerLayout) {
            this.activity = activity;
            this.drawerLayout = drawerLayout;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.nav_categories) {
                Intent intent = new Intent(getApplicationContext(), ListCategoryActivity.class);
                startActivity(intent);
            } else if (id == R.id.nav_add_category) {
                activity.startActivity(new Intent(activity, AddEventCategoryActivity.class));
            } else if (id == R.id.nav_events) {
                activity.startActivity(new Intent(activity, ListEventActivity.class));
            } else if (id == R.id.nav_logout) {
                logout();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        private void logout() {
            // Implement your logout logic here
            activity.startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        }
    }

}
