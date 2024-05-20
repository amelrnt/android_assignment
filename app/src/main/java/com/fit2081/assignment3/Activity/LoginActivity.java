package com.fit2081.assignment3.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.fit2081.assignment3.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Match the XML file name

        // Binding the views with their IDs
        editTextUsername = findViewById(R.id.editTextUsername2);
        editTextPassword = findViewById(R.id.editTextPassword2);
        Button buttonLogin = findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // Prefill the username field with the last saved username
        editTextUsername.setText(sharedPreferences.getString("LastUsername", ""));

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    private void authenticateUser() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        String storedUsername = sharedPreferences.getString("Username", "");
        String storedPassword = sharedPreferences.getString("Password", "");

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            // Save the last successful username
            sharedPreferences.edit().putString("LastUsername", username).apply();

            // Login success: Redirect to DashboardActivity
            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent dashboardIntent = new Intent(LoginActivity.this, DashboardActivity.class);
            dashboardIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(dashboardIntent);
            Log.d("Activity", "after login");
//            finish();
        } else {
            // Login failure
            Toast.makeText(LoginActivity.this, "Authentication failure: Username or Password incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
