package com.example.android_project_final;

//import static com.example.android_project_final.database.ApplicationRepository.repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project_final.database.ApplicationDatabase;
import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.MealDAO;
import com.example.android_project_final.database.entities.Ingredients;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.databinding.ActivityAddMealBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddMealActivity extends AppCompatActivity {
//    public String name;
//    public float protein;
//    public float carbs;
//    public float fats;
//    public float calories;

    private ApplicationRepository repository;
    private ActivityAddMealBinding binding;
    private MealDAO mealDAO;
    List<String> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMealBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ApplicationRepository.getRepository(getApplication());

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMealToDB();
                Toast.makeText(AddMealActivity.this, "Submitting...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveMealToDB() {
        String name = binding.enterMealInputEditText.getText().toString().trim();
        String proteinStr = binding.enterProteinInputEditText.getText().toString().trim();
        String carbsStr = binding.enterCarbsInputEditText.getText().toString().trim();
        String fatsStr = binding.enterFatsInputEditText.getText().toString().trim();
        String caloriesStr = binding.enterCaloriesInputEditText.getText().toString().trim();
        if(name.isEmpty() || proteinStr.isEmpty() || carbsStr.isEmpty() || fatsStr.isEmpty() || caloriesStr.isEmpty()){
            Toast.makeText(this, "Error, please fill in all information!", Toast.LENGTH_SHORT).show();
            return;
        }
        int chicken = 0;
        int beef = 0;
        int fish = 0;
        int rice = 0;
        int greensA = 0;
        int greensB = 0;
        int greensC = 0;
        if(binding.chickenCheckBox.isChecked()){
            chicken = 1;
        }
        if(binding.beefCheckBox.isChecked()){
            beef = 1;
        }
        if(binding.fishCheckBox.isChecked()){
            fish = 1;
        }
        if(binding.riceCheckBox.isChecked()){
            rice = 1;
        }
        if(binding.greensACheckBox.isChecked()){
            greensA = 1;
        }
        if(binding.greensBCheckBox.isChecked()){
            greensB = 1;
        }
        if(binding.greensCCheckBox.isChecked()){
            greensC = 1;
        }

        Ingredients ingredients = new Ingredients(beef, chicken, fish, rice, greensA, greensB, greensC);
        repository.insertIngredients(ingredients);

        int protein = Integer.parseInt(proteinStr);
        int carbs = Integer.parseInt(carbsStr);
        int fats = Integer.parseInt(fatsStr);
        int calories = Integer.parseInt(caloriesStr);

        Meal meal = new Meal(name, protein, carbs, fats, calories);
        repository.insertMeal(meal);

    }
//        if(mealNameEditTextView.isEmpty()){
//            return;
//        }
//        Meal meal = new Meal(mealNameEditTextView, proteinEditTextView, carbsEditTextView, fatsEditTextView, caloriesEditTextView);
//        repository.insertMeal(meal);
//    }

    public static Intent addMealIntentFactory(Context applicationContext) {
        return new Intent(applicationContext, AddMealActivity.class);
    }
}