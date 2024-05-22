package com.fit2081.assignment3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.FragmentListCategory;
import com.fit2081.assignment3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private List<Event> eventList = new ArrayList<>();
    private EditText editTextEventID;
    private EditText editTextEventName;
    private EditText editTextCategoryId;
    private EditText editTextTicketsAvailable;
    private Switch switchIsActive;
    private FloatingActionButton fabSaveEvent;
    private EventViewModel eventViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        // UI Binding
        drawerLayout = findViewById(R.id.drawer_layout);
        editTextEventID = findViewById(R.id.editTextEventID2);
        editTextEventName = findViewById(R.id.editTextEventName2);
        editTextCategoryId = findViewById(R.id.editTextCategoryid2);
        editTextCategoryId = findViewById(R.id.editTextCategoryid2);
        editTextTicketsAvailable = findViewById(R.id.editTextTicketsAvailable2);
        switchIsActive = findViewById(R.id.is_active_toggle);
        fabSaveEvent = findViewById(R.id.fab);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.inflateMenu(R.menu.drawer_menu);
        navigationView.setNavigationItemSelectedListener(item -> {
            handleNavigation(item);
            return true;
        });

//        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(EventViewModel.class);

        // Insert a new event category and event
        EventCategory category = new EventCategory("Test12", "Cat1", 1, true, "Jakarta");
        eventViewModel.insert(category);

        // You can observe the categories to get the ID of the newly inserted category
        eventViewModel.getAllCategories().observe(this, new Observer<List<EventCategory>>() {
            @Override
            public void onChanged(List<EventCategory> categories) {
                for (EventCategory cat : categories) {
                    if (cat.getName().equals("Music")) {
                        Event event = new Event("E001", "Test12", "Event1", 2, true);
                        eventViewModel.insert(event);
                        break;
                    }
                }
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentListCategory fragmentListCategory = new FragmentListCategory();
//        fragmentTransaction.replace(R.id.frameLayout, fragmentListCategory);
        fragmentTransaction.commit();

        // Assuming app_bar_layout includes a Toolbar with id toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fabSaveEvent.setOnClickListener(view -> {
            // Perform the save operation
            Log.e("DashboardActivity", "EventViewModel initialized: " + (eventViewModel != null));

            Log.d("Activity", "on fab create event click");
            saveEvent();
        });
    }

    private void handleNavigation(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_categories) {
            Intent toListCategory = new Intent(DashboardActivity.this, ListCategoryActivity.class);
            startActivity(toListCategory);
            Log.d("Activity", "nav click ");
        } else if (id == R.id.nav_add_category) {
            Intent toAddCategory = new Intent(DashboardActivity.this, AddEventCategoryActivity.class);
            startActivity(toAddCategory);
            Log.d("Activity", "add category");
        } else if (id == R.id.nav_events) {
            Intent toListEvent = new Intent(DashboardActivity.this, ListEventActivity.class);
            startActivity(toListEvent);
            Log.d("Activity", "list event");
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Log.d("Activity", "logout");
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void saveEvent() {
        if(editTextEventName.getText().toString().isEmpty()
                || editTextCategoryId.getText().toString().isEmpty()
                || editTextTicketsAvailable.getText().toString().isEmpty() ){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            String eventId = generateEventId();
            Log.d("Activity", eventId);
            editTextEventID.setText(eventId);
            // Validate and correct tickets available
            String ticketsAvailableStr = editTextTicketsAvailable.getText().toString();
            Log.d("Activity", ticketsAvailableStr);
            String eventName = editTextEventName.getText().toString();
            // Validate event name
            if (!validateEventName(eventName)) {
                Toast.makeText(this, "Invalid event name", Toast.LENGTH_SHORT).show();
            }
            Log.d("Activity", eventName);
            int eventCount = Integer.parseInt(editTextTicketsAvailable.getText().toString());
            Log.d("Activity", editTextTicketsAvailable.getText().toString());
            String categoryId = editTextCategoryId.getText().toString();
            Log.d("Activity", categoryId);
            boolean isActive = switchIsActive.isChecked();
            // Insert a new event
            eventViewModel.validateCategory(categoryId).observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isValid) {
                    if (isValid) {
                        // Category is valid, insert the event
                        Event event = new Event(eventId, categoryId, eventName, eventCount, isActive);
                        eventViewModel.insert(event);
                        // Update selected category count +1
//                        eventViewModel.getCategoryById(categoryId).observe(DashboardActivity.this, new Observer<EventCategory>() {
//                            @Override
//                            public void onChanged(EventCategory category) {
//                                if (category != null) {
//                                    category.setCategoryCount(category.getCategoryCount() + 1);
//                                    eventViewModel.update(category);
//                                }
//                            }
//                        });
                    } else {
                        // Category is not valid, show error message or handle the case accordingly
                        Toast.makeText(DashboardActivity.this, "Invalid Category ID", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // Success message
//        Toast.makeText(this, "Event saved: " + "eventName" + " to " + categoryId, Toast.LENGTH_LONG).show();
    }

    private String generateEventId() {
        Random random = new Random();
        // Generate two random uppercase letters
        char randomChar1 = (char) ('E' + random.nextInt(26));
        char randomChar2 = (char) ('E' + random.nextInt(26));
        // Generate four random digits
        int randomDigits = 1000 + random.nextInt(9000);
        return "C" + randomChar1 + randomChar2 + "-" + randomDigits;
    }

    private boolean validateEventName(String eventName) {
        return !eventName.matches("\\d+") && !eventName.contains("%");
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


}
