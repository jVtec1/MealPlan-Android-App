package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.databinding.ActivityFindMealBinding;

import java.util.ArrayList;
import java.util.List;

public class FindMealActivity extends AppCompatActivity {
    private ActivityFindMealBinding binding;
    private ApplicationRepository repository;
    private RecyclerView recyclerView;
//    private MealAdapter adapter;

    private List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ApplicationRepository.getRepository(getApplication());

//        adapter = new MealAdapter(new ArrayList<>(), FindMealActivity.this);
//        binding.mealRecyclerView.setLayoutManager(new LinearLayoutManager(FindMealActivity.this));
//        binding.mealRecyclerView.setAdapter(adapter);


        loadMealsFromDB();
    }

    private void loadMealsFromDB() {
        meals = repository.getAllMeals();

    }


    public static Intent findMealIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, FindMealActivity.class);
    }
}