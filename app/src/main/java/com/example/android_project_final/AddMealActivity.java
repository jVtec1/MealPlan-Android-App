package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project_final.database.MealDAO;

import java.util.List;

public class AddMealActivity extends AppCompatActivity {

    private String mealName;
    private float protein;
    private float carbs;
    private float fats;
    private float calories;
    private List<String> ingredients;
    private MealDAO mealDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_meal);
    }

    public static Intent addMealIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, AddMealActivity.class);
    }
}