package com.example.android_project_final.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android_project_final.database.ApplicationDatabase;

import java.util.Objects;

@Entity(tableName = ApplicationDatabase.MEAL_TABLE) // version : 1
public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int mealId; // id that connects to this meal's list of ingredients in the ingredient table
    private String mealName;
    private double price;
    private int calories;
    private int protein; // grams
    private int fat; // grams
    private int carbs; // grams
    private int cholesterol; // milligrams

    Meal(String name, double price, int cals, int protein, int fat, int carbs, int cholesterol){
        this.mealName = name;
        this.price = price;
        this.calories = cals;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.cholesterol = cholesterol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return mealId == meal.mealId && Double.compare(price, meal.price) == 0 && calories == meal.calories && protein == meal.protein && fat == meal.fat && carbs == meal.carbs && cholesterol == meal.cholesterol && Objects.equals(mealName, meal.mealName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, mealName, price, calories, protein, fat, carbs, cholesterol);
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public int getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(int cholesterol) {
        this.cholesterol = cholesterol;
    }
}
