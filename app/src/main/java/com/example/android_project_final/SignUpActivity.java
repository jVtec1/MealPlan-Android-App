package com.example.android_project_final;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project_final.database.ApplicationRepository;
import com.example.android_project_final.databinding.ActivityLoginBinding;
import com.example.android_project_final.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private ApplicationRepository repository;



    static Intent signupIntentFactory(Context context){
        return new Intent(context, SignUpActivity.class);
    }
}
