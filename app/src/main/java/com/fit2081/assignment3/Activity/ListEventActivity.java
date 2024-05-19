package com.fit2081.assignment3.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fit2081.assignment3.FragmentListCategory;
import com.fit2081.assignment3.FragmentListEvent;
import com.fit2081.assignment3.R;

public class ListEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Load FragmentListEvent into the container
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentListEvent fragmentListEvent = new FragmentListEvent();
        FragmentListCategory fragmentListCategory = new FragmentListCategory();
//        fragmentTransaction.replace(R.id.frameLayout, fragmentListEvent);
        fragmentTransaction.commit();
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
