package com.fit2081.assignment3.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.fit2081.assignment3.Data.EventCategory;
import com.fit2081.assignment3.EventViewModel;
import com.fit2081.assignment3.R;

import java.util.Random;

public class AddEventCategoryActivity extends AppCompatActivity {

    EditText editTextCategoryID, editTextCategoryName, editTextEventCount, editTextLocation;
    Switch isActiveSwitch;
    Button buttonSaveCategory;
    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_category);
        initViews();
        setupSaveCategoryButton();
    }

    private void initViews() {
        editTextCategoryID = findViewById(R.id.editTextCategoryID);
        editTextCategoryName = findViewById(R.id.editTextCategoryName);
        editTextEventCount = findViewById(R.id.editTextEventCount);
        isActiveSwitch = findViewById(R.id.isActive);
        buttonSaveCategory = findViewById(R.id.buttonSaveCategory);
        editTextLocation = findViewById(R.id.edit_text_location);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
    }

    private void setupSaveCategoryButton() {
        buttonSaveCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCategory();
            }
        });
    }

    private void saveCategory() {
        if(editTextCategoryName.getText().toString().isEmpty()
                || editTextEventCount.getText().toString().isEmpty()
                || editTextLocation.getText().toString().isEmpty() ){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else{
            String categoryId = generateCategoryId();
            Log.d("generatedID", categoryId);
            editTextCategoryID.setText(categoryId);
            String categoryName = editTextCategoryName.getText().toString();
            int eventCount = Integer.parseInt(editTextEventCount.getText().toString());
            boolean isActive = isActiveSwitch.isChecked();
            String location = editTextLocation.getText().toString();
            // Insert a new event
            EventCategory category = new EventCategory(categoryId,categoryName,eventCount,isActive,location);
            eventViewModel.insert(category);
        }
    }
    private String generateCategoryId() {
        Random random = new Random();
        // Generate two random uppercase letters
        char randomChar1 = (char) ('A' + random.nextInt(26));
        char randomChar2 = (char) ('A' + random.nextInt(26));
        // Generate four random digits
        int randomDigits = 1000 + random.nextInt(9000);
        return "C" + randomChar1 + randomChar2 + "-" + randomDigits;
    }

    private boolean isValidCategoryName(String name) {
        // Check if the name is not just digits and does not contain special characters
        return name.matches("[A-Za-z ]+") && !name.matches("\\d+");
    }
}

