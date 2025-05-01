package com.example.android_project_final.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android_project_final.database.ApplicationDatabase;
import com.example.android_project_final.database.ApplicationRepository;

@Entity(tableName = ApplicationDatabase.MEAL_TABLE)
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int mealId;
}
