package com.fit2081.assignment3.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.fit2081.assignment3.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;
    private Button onRegisterButtonClick, onLoginButtonClick;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        onRegisterButtonClick = findViewById(R.id.onRegisterButtonClick);
        onLoginButtonClick = findViewById(R.id.onLoginButtonClick);

        // safely restore state
        if (savedInstanceState != null) {
            editTextUsername.setText(savedInstanceState.getString("username"));
            editTextPassword.setText(savedInstanceState.getString("password"));
            editTextConfirmPassword.setText(savedInstanceState.getString("confirmPassword"));
        }

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        onRegisterButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        onLoginButtonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Basic validation
        if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save credentials
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.apply();

        // Show success message and navigate to LoginActivity
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();


    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current state
        outState.putString("username", editTextUsername.getText().toString());
        outState.putString("password", editTextPassword.getText().toString());
        outState.putString("confirmPassword", editTextConfirmPassword.getText().toString());
    }
}

