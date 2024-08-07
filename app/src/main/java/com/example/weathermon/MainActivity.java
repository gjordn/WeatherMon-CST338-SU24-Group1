package com.example.weathermon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.weathermon.MAIN_ACTIVITY_USER_ID";
    private static final String TAG = "MainActivity";
    private int loggedInUserId = -1;
    private WeathermonRepository repository;
    private User user;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonMyPetWeathermon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = UserCardMaintenanceActivity.userCardMaintenanceIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        binding.buttonTrainWeathermon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TrainWeathermonActivity.trainWeathermonMaintenanceIntentFactory(getApplicationContext(), loggedInUserId);
                startActivity(intent);
            }
        });

        // Ensure this is correctly set to launch WeathermonDexActivity
        binding.buttonTheWeathermon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeathermonDexActivity.class);
                startActivity(intent);
            }
        });

        int userId = getUserIdFromPrefs();
        Log.d(TAG, "Retrieved User ID from prefs: " + userId);  // Debugging log
        if (userId == -1) {
            Log.d(TAG, "User ID from prefs: " + userId);  // Additional log for debugging
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
            finish();
            return;
        }

        loggedInUserId = userId;
        repository = WeathermonRepository.getRepository(getApplication());

        if (repository == null) {
            Log.e(TAG, "Repository initialization failed");
            finish();
            return;
        }

        LiveData<User> userObserver = repository.getUserByUserID(loggedInUserId);
        userObserver.observe(this, user -> {
            if (user != null) {
                this.user = user;
                Log.d(TAG, "User data loaded: " + user.getUsername());
                if (user.isAdmin()) {
                    binding.buttonAdministrator.setVisibility(View.VISIBLE);
                } else {
                    binding.buttonAdministrator.setVisibility(View.GONE);
                }
            }
        });

        binding.buttonAdministrator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AdminActivity.adminActivityIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        if (user != null) {
            item.setTitle(user.getUsername());
        }
        item.setOnMenuItem




