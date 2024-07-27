package com.example.weathermon;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    int loggedInUserId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginUser();

        if(loggedInUserId == -1){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }


        setContentView(R.layout.activity_main);

    }

    private void loginUser() {
        //TODO create login method.
    }
}