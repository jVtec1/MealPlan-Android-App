package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;

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
    private List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ApplicationRepository.getRepository(getApplication());

        binding.logDisplayTextView.setMovementMethod(new ScrollingMovementMethod());
        updateDisplay();

    }
    

    private void updateDisplay(){
        ArrayList<Meal> allLogs = repository.getAllMeals();
        if(allLogs.isEmpty()){
            binding.FindMealViewModel.setText("nothing here");
        }
        StringBuilder sb = new StringBuilder();
        for(Meal log : allLogs){
            sb.append(log);
        }

        binding.logDisplayTextView.setText(sb.toString());
    }

    public static Intent findMealIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, FindMealActivity.class);
    }
}