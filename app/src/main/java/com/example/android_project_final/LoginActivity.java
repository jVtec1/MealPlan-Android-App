package com.example.android_project_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project_final.database.ApplicationDatabase;
import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.database.UserDAO;
import com.example.android_project_final.database.entities.User;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private ApplicationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText= findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        ApplicationDatabase db = ApplicationDatabase.getDatabase(getApplicationContext());

        repository = new ApplicationRepository(db.userDAO());

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                authenticateUser(username, password);
            }
        });
    }

        private void authenticateUser(String username, String password) {
            User user = repository.getUserByUsername(username);

             if (user != null && user.getPassword().equals(password)){
                 Intent intent;
                 if(user.isAdmin()) {
                     intent=  new Intent(LoginActivity.this, AdminActivity.class);
                 }
                 else {
                     intent = new Intent(LoginActivity.this, MainActivity.class);
                 }
                 startActivity(intent);
                 finish();
            }
             else {
                 Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
             }

        }
        //Some of this needs methods and another class this is the base of what we are making however.


    }
