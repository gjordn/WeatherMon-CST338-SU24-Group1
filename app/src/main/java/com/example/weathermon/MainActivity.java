package com.example.weathermon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.weathermon.MAIN_ACTIVITY_USER_ID";
    private static final String TAG = "MainActivity";
    private int loggedInUserId = -1;
    private WeathermonRepository repository;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            }
        });

        invalidateOptionsMenu();
    }

    private int getUserIdFromPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("WeatherMonPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);
        Log.d(TAG, "Retrieved User ID from prefs: " + userId);  // Debugging log
        return userId;
    }

    // Main Activity Factory
    static Intent mainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}