package com.example.android_project_final;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LandingPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("admin1", "admin1");

        TextView welcomeUserTextViewLabel = findViewById(R.id.welcomeUserTextViewLabel);

        welcomeUserTextViewLabel.setText("Welcome " + username+ "!");
    }
}