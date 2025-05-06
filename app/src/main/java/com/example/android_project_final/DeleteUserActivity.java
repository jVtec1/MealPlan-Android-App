package com.example.android_project_final;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project_final.database.ApplicationRepository;

public class DeleteUserActivity extends AppCompatActivity {
    private EditText editTextUserId;

    private Button buttonDeleteUser;
    private ApplicationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        editTextUserId = findViewById(R.id.editTextUserId);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        repository = ApplicationRepository.getRepository(getApplication());

        buttonDeleteUser.setOnClickListener(v ->{
            String idText = editTextUserId.getText().toString().trim();
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
}
