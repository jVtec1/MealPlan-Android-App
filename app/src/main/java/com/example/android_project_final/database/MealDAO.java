package com.example.android_project_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_project_final.database.entities.Meal;

import java.util.List;

@Dao
public interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meal meal);

    @Query("SELECT * FROM " + ApplicationDatabase.MEAL_TABLE)
    List<Meal> getAllMeals();

    @Query("SELECT * FROM " + ApplicationDatabase.MEAL_TABLE + " WHERE mealName = :name ORDER BY mealName ASC")
    LiveData<Meal> getMealByName(String name);

    @Query("DELETE from " + ApplicationDatabase.MEAL_TABLE)
    void deleteAll();
}
