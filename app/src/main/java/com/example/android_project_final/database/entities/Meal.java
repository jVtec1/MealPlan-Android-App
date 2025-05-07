package com.example.android_project_final.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android_project_final.database.ApplicationDatabase;

import java.util.Objects;

@Entity(tableName = ApplicationDatabase.MEAL_TABLE) // version : 1
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int mealId; // id that connects to this meal's list of ingredients in the ingredient table
    private String mealName;
    private int calories;
    private int protein; // grams
    private int fat; // grams
    private int carbs; // grams

    public Meal(String name, int cals, int protein, int fat, int carbs){
        this.mealName = name.toLowerCase();
        this.calories = cals;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public Meal(){
        this.mealName = null;
        this.calories = 0;
        this.protein = 0;
        this.fat = 0;
        this.carbs = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return getMealId() == meal.getMealId() && getCalories() == meal.getCalories() && getProtein() == meal.getProtein() && getFat() == meal.getFat() && getCarbs() == meal.getCarbs() && Objects.equals(getMealName(), meal.getMealName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMealId(), getMealName(), getCalories(), getProtein(), getFat(), getCarbs());
    }

    @NonNull
    @Override
    public String toString() {
        return "Meal: " + mealName + " -- MealID: " + mealId + " -- Calories: " + calories + " -- Protein: " + protein + "g -- Fats: " + fat + "g -- Carbs: " + carbs + "g\n\n";

    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }
}
