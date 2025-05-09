package com.example.android_project_final;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.entities.User;
import com.example.android_project_final.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {
    private static final String LANDING_ACTIVITY_USER_ID = "com.example.android_project_final.LANDING_ACTIVITY_USER_ID";
    private static final String LANDING_ACTIVITY_USER_NAME = "com.example.android_project_final.LANDING_ACTIVITY_USER_NAME";

    private static final int LOGGED_OUT = -1;
    private ApplicationRepository repository;
    private int loggedInUserId = -1;
    private User user;
    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ApplicationRepository.getRepository(getApplication());

        initUser();

        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }
        updateSharedPreference();

        binding.findMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FindMealActivity.findMealIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddMealActivity.addMealIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.proteinRecommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProteinRecommendationDialog();
            }
        });

    }

    private void initUser(){
        loggedInUserId = getIntent().getIntExtra(LANDING_ACTIVITY_USER_ID, -1);
        LiveData<User> userObserver = repository.getUserByUserId(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
//            showAdmin();
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(MainActivity.SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
    }

    private void updateSharedPreference(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor= sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key),loggedInUserId);
        sharedPrefEditor.apply();
    }

    private void logout() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        if(user == null){
            return false;
        }
        item.setTitle(user.getUsername());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                showLogoutDialog();
                return false;
            }
        });
        return true;
    }

    private void showLogoutDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(LandingActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertDialog.setMessage("Logout");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    static Intent landingActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, LandingActivity.class);
        intent.putExtra(LANDING_ACTIVITY_USER_ID, userId);
        return intent;
    }

    private void showProteinRecommendationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your weight in pounds");

        final EditText input = new EditText(this);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Calculate", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weightInput = input.getText().toString().trim();
                if (!weightInput.isEmpty()) {
                    try {
                        int weight = Integer.parseInt(weightInput);

                        int protein = (int) (weight * 0.8);
                        showProteinResult(protein);
                    } catch (NumberFormatException e) {
                        Toast.makeText(LandingActivity.this, "Invalid weight", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LandingActivity.this, "Enter your weight", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }
    private void showProteinResult(int protein) {
        new AlertDialog.Builder(this)
                .setTitle("Protein Recommendation")
                .setMessage("You should consume at least " + protein + " grams of protein daily")
                .setPositiveButton("OK", null)
                .show();
    }

}