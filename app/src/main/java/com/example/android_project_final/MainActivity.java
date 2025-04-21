package com.example.android_project_final;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
//import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    //TextView appTitle;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        //setTitle("makros");

        loginBtn.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("admin") && pass.equals("1234")) {
                Intent intent = new Intent(MainActivity.this, WelcomeAdmin.class);
                startActivity(intent);
                finish(); //closes MainActivity so user can't go back
                //Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}