package com.example.weathermon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;


public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.weathermon.MAIN_ACTIVITY_USER_ID";
    private int loggedInUserId = -1;
    private WeathermonRepository repository;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int userId = getUserIdFromPrefs();
        if (userId == -1) {
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
            finish();
            return;
        }

        //Sets login in userId.
        loggedInUserId = userId;
        repository = WeathermonRepository.getRepository(getApplication());
        //Handles null repository case.
        if(repository == null){
            finish();
            return;
        }

        //Observes user data.
        LiveData<User> userObserver = repository.getUserByUserID(loggedInUserId);
        userObserver.observe(this, user -> {
            if (user != null) {
                //Sets user data.
                this.user = user;
            }
        });
        //Refreshes options menu.
        invalidateOptionsMenu();
    }

    private int getUserIdFromPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("WeatherMonPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }

    //Main Activity Factory
    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = WeathermonRepository.getRepository(getApplication());
        loginUser();

        LiveData<User> userObserver = repository.getUserByUserID(1);
        userObserver.observe(this, user -> {
            //Find user, if found, set shared preferences to userID and reset menu options
            if (user != null){
                this.user = user;
            }
        });

        invalidateOptionsMenu();

        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

    }



    private void loginUser() {
        //TODO create login method.
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }
    */

}