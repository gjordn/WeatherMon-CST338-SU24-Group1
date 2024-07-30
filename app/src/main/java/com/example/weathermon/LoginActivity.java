package com.example.weathermon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weathermon.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

   private ActivityLoginBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 1;
                saveUserCredentials(userId);
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), userId);
                startActivity(intent);
            }
        });
    }

    private void saveUserCredentials(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("WeatherMonPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}