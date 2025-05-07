package com.example.android_project_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_project_final.database.entities.Ingredients;
import com.example.android_project_final.database.entities.Meal;

import java.util.List;

@Dao
public interface IngredientsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ingredients ingredients);

    @Query("SELECT * FROM " + ApplicationDatabase.INGREDIENTS_TABLE + " WHERE mealId = :mealId")
    LiveData<Ingredients> getIngredientsByMealId(int mealId);

    @Query("SELECT * FROM " + ApplicationDatabase.INGREDIENTS_TABLE)
    List<Ingredients> getAllIngredients();

    @Query("DELETE from " + ApplicationDatabase.INGREDIENTS_TABLE)
    void deleteAll();
}
