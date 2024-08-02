package com.example.weathermon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;

public class AdminActivity extends AppCompatActivity {

    private WeathermonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        repository = WeathermonRepository.getRepository(getApplication());

        Button createUserButton = findViewById(R.id.createUserButton);
        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        Button deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        Button makeAdminButton = findViewById(R.id.makeAdminButton);
        makeAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAdmin();
            }
        });

        Button changePasswordButton = findViewById(R.id.changePasswordButton);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void createUser() {
        // Open a dialog or a new activity to get user details and create a new user
        Toast.makeText(this, "Create User button clicked", Toast.LENGTH_SHORT).show();
    }

    private void deleteUser() {
        // Open a dialog or a new activity to get the username and delete the user
        Toast.makeText(this, "Delete User button clicked", Toast.LENGTH_SHORT).show();
    }

    private void makeAdmin() {
        // Open a dialog or a new activity to get the username and make the user an admin
        Toast.makeText(this, "Make Admin button clicked", Toast.LENGTH_SHORT).show();
    }

    private void changePassword() {
        // Open a dialog or a new activity to get the username and change the user's password
        Toast.makeText(this, "Change Password button clicked", Toast.LENGTH_SHORT).show();
    }

    static Intent adminActivityIntentFactory(Context context) {
        return new Intent(context, AdminActivity.class);
    }
}