package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.database.entities.User;
import com.example.android_project_final.databinding.ActivityDeleteUserBinding;

import java.util.ArrayList;

public class DeleteUserActivity extends AppCompatActivity {
    private EditText editTextUserId;

    private Button buttonDeleteUser;
    private ApplicationRepository repository;

    private ActivityDeleteUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = ApplicationRepository.getRepository(getApplication());

        updateDisplay();

        binding.buttonDeleteUser.setOnClickListener(v ->{
            String idText = binding.editTextUserId.getText().toString().trim();
            if(!idText.isEmpty()){
                int userId = Integer.parseInt(idText);
                repository.deleteUserId(userId);
                Toast.makeText(this, "User Deleted!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Enter a User ID", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateDisplay(){
        ArrayList<User> allLogs = repository.getAllUsers();
        if(allLogs.isEmpty()){
            binding.FindMealViewModel.setText("nothing here");
        }
        StringBuilder sb = new StringBuilder();
        for(User log : allLogs){
            sb.append(log);
        }

        binding.logDisplayTextView.setText(sb.toString());
    }

    public static Intent DeleteUserActivityFactory(Context context){
        return new Intent(context, DeleteUserActivity.class);
    }
}
