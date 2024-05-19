package com.fit2081.assignment3.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.fit2081.assignment3.FragmentListCategory;
import com.fit2081.assignment3.R;


public class ListCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);

        // Initialize toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Load FragmentListCategory into the container
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentListCategory fragmentListCategory = new FragmentListCategory();
//        fragmentTransaction.replace(R.id.frameLayout, fragmentListCategory);
        fragmentTransaction.commit();
    }
}
