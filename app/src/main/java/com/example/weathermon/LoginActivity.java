/**
 * Gerek G. Jordan
 * CST338 Software Design Summer 24
 * https://github.com/gjordn/WeatherMon-CST338-SU24-Group1.git
 */
package com.example.weathermon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private WeathermonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WeathermonRepository.getRepository(getApplication());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.userNameLoginEditText.getText().toString();
                String password = binding.passwordLoginEditText.getText().toString();
                validateCredentials(username, password);
            }
        });
    }

    /**
     * Method to validate user credentials.
     */
    private void validateCredentials(String username, String password) {
        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, user -> {
            if (user != null) {
                int userId = user.getId();
                Log.d(TAG, "Fetched User ID: " + userId);
                if (password.equals(user.getPassword())) {
                    saveUserCredentials(userId);
                    Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Method that saves user credentials in SharedPreferences.
     */
    private void saveUserCredentials(int userId) {
        if (userId != -1) {
            SharedPreferences sharedPreferences = getSharedPreferences("WeatherMonPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("userId", userId);
            editor.apply();
            Log.d(TAG, "Saved User ID: " + userId);  // Debugging log
        } else {
            Log.e(TAG, "Failed to save user ID, invalid user ID: " + userId);
        }
    }

    /**
     * Factory method to create Intent for LoginActivity.
     */
    static Intent loginIntentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}
