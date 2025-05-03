package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.entities.User;
import com.example.android_project_final.databinding.ActivityLoginBinding;
import com.example.android_project_final.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private ApplicationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivitySignupBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            repository = ApplicationRepository.getRepository(getApplication());

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp();
            }

        });



    }

    private void handleSignUp(){
        String username = binding.editTextUsername.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Username and password required", Toast.LENGTH_SHORT).show();
            return;
        }

        repository.getUserByUserName(username).observe(this, exisitingUser -> {
            if(exisitingUser != null){
                Toast.makeText(this, "Username already being used", Toast.LENGTH_SHORT).show();
            }else {
                User newUser = new User(username, password);
                repository.insertUser(newUser);
                Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();
                startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
                finish();
            }
        });
    }

    public static Intent signUpFactory(Context context){
        return new Intent(context, SignUpActivity.class);
    }

}
