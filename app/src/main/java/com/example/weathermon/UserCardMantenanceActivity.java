package com.example.weathermon;

import static com.example.weathermon.database.Util.WEATHERMON_LOGGED_IN_USER_ID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityUserCardMantenanceBinding;
import com.example.weathermon.viewholders.CardMaintenanceViewModel;

public class UserCardMantenanceActivity extends AppCompatActivity {
    ActivityUserCardMantenanceBinding binding;
    private WeathermonRepository repository;
    private CardMaintenanceViewModel cardViewModel;


    private int loggedInUserID = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UserCardMantenanceActivity.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

static Intent userCardMaintenanceIntentFactory(Context context, int userID){
    Intent intent = new Intent(context, MainActivity.class);
    intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, userID);
    return intent;
}

}