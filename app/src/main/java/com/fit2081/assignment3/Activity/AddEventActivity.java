package com.fit2081.assignment3.Activity;


import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fit2081.assignment3.Data.Event;
import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.R;

import java.util.List;
import java.util.Random;

public class AddEventActivity extends AppCompatActivity {

    EditText editTextEventID, editTextEventName, editTextEventCount, editTextCategoryID;
    Switch isActiveSwitch;
    Button buttonSaveEvent;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        initViews();
        setupSaveCategoryButton();
    }

    private void initViews() {
        editTextEventID = findViewById(R.id.editTextEventID);
        editTextEventName = findViewById(R.id.editTextEventName);
        editTextCategoryID = findViewById(R.id.editTextCategoryId);
        editTextEventCount = findViewById(R.id.editTextTicketsAvailable);
        isActiveSwitch = findViewById(R.id.switchIsActive);
        buttonSaveEvent = findViewById(R.id.switchIsActive);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
    }

    private void setupSaveCategoryButton() {
        buttonSaveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEvent();
            }
        });
    }

    private void saveEvent() {
        String eventId = generateEventId();
        Log.d("generatedID", "categoryId");
        editTextEventID.setText(eventId);
        String eventName = editTextEventName.getText().toString();
        int eventCount = Integer.parseInt(editTextEventCount.getText().toString());
        String categoryId = editTextCategoryID.getText().toString();
        boolean isActive = isActiveSwitch.isChecked();
        // Insert a new event
        eventViewModel.validateCategory(categoryId).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isValid) {
                if (isValid) {
                    // Category is valid, insert the event
                    Event event = new Event(eventId, categoryId, eventName, eventCount, isActive);
                    eventViewModel.insert(event);
                    // Update selected category count +1
                    eventViewModel.getCategoryById(categoryId).observe(AddEventActivity.this, new Observer<EventCategory>() {
                        @Override
                        public void onChanged(EventCategory category) {
                            if (category != null) {
                                category.setCategoryCount(category.getCategoryCount() + 1);
                                eventViewModel.update(category);
                            }
                        }
                    });
                } else {
                    // Category is not valid, show error message or handle the case accordingly
                    Toast.makeText(AddEventActivity.this, "Invalid Category ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private boolean isValidCategoryName(String name) {
        // Check if the name is not just digits and does not contain special characters
        return name.matches("[A-Za-z ]+") && !name.matches("\\d+");
    }
}

