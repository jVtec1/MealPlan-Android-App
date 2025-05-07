package com.example.android_project_final;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project_final.databinding.ActivityWelcomeAdminBinding;

public class WelcomeAdmin extends AppCompatActivity {


    private ActivityWelcomeAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.adminGreeting.setText("Hello, Admin");


        binding.deleteUserButton.setOnClickListener(v -> {
            Intent intent = DeleteUserActivity.DeleteUserActivityFactory(getApplicationContext());
            startActivity(intent);
        });

        binding.addmealButton.setOnClickListener(v -> {
            Intent intent = AddMealActivity.addMealIntentFactory(getApplicationContext());
            startActivity(intent);
        });

        binding.findmealbutton.setOnClickListener(v -> {
            Intent intent = FindMealActivity.findMealIntentFactory(getApplicationContext());
            startActivity(intent);
        });
    }

    public static Intent WelcomeAdminFactory(Context context, int userId){
        return new Intent(context, WelcomeAdmin.class);
    }
}