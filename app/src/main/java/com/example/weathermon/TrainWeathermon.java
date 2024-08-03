package com.example.weathermon;

import static com.example.weathermon.api.WeatherstackInterface.BASE_URL;
import static com.example.weathermon.api.WeatherstackInterface.ENGLISH_UNITS;
import static com.example.weathermon.database.Util.LOGGING_TAG;
import static com.example.weathermon.database.Util.USER_LOGGED_OUT;
import static com.example.weathermon.database.Util.WEATHERMON_LOGGED_IN_USER_ID;
import static com.example.weathermon.database.Util.WEATHERMON_SHARED_PREF_KEY;
import static com.example.weathermon.database.Util.WEATHERMON_SHARED_PREF_USERID;

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

import com.example.weathermon.api.WeatherstackInterface;
import com.example.weathermon.api.WeatherstackWeatherHolder;
import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityTrainWeathermonBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrainWeathermon extends AppCompatActivity {
    ActivityTrainWeathermonBinding binding;
    private WeathermonRepository repository;

    private int loggedInUserID = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainWeathermonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = WeathermonRepository.getRepository(getApplication());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginUser(savedInstanceState);

        WeatherstackInterface weatherstackInterface = retrofit.create(WeatherstackInterface.class);
        weatherstackInterface.getWeartherstackWeather("Shanghai", ENGLISH_UNITS).enqueue(new Callback<WeatherstackWeatherHolder>() {
            @Override
            public void onResponse(@NonNull Call<WeatherstackWeatherHolder> call, @NonNull Response<WeatherstackWeatherHolder> response) {
                Log.d(LOGGING_TAG, "icons" +response.body().current.weather_icons[0]);
                Log.d(LOGGING_TAG, "is_day" +response.body().current.weather_descriptions[0]);
                Log.d(LOGGING_TAG, "units" +response.body().request.unit);
                Log.d(LOGGING_TAG, "temp" +response.body().current.temperature);

            }

            @Override
            public void onFailure(Call<WeatherstackWeatherHolder> call, Throwable throwable) {
                binding.weatherTextView.setText("throwable: " + throwable.getMessage());
            }
        });

        binding.buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUserID);
                startActivity(intent);
            }
        });
    }

    private void loginUser(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(WEATHERMON_SHARED_PREF_KEY,
                Context.MODE_PRIVATE);
        loggedInUserID = sharedPreferences.getInt(WEATHERMON_SHARED_PREF_USERID, USER_LOGGED_OUT);

        if (loggedInUserID == USER_LOGGED_OUT) {
            //Check intent for logged in user if not found in sharedPreferences.
            loggedInUserID = getIntent().getIntExtra(WEATHERMON_LOGGED_IN_USER_ID, USER_LOGGED_OUT);
        }

        if (loggedInUserID == USER_LOGGED_OUT) {
            return;  //No user id, exit out.
        }

        LiveData<User> userObserver = repository.getUserByUserID(loggedInUserID);
        userObserver.observe(this, user -> {
            //Find user, if found, set shared preferences to userID and reset menu options
            if (user != null) {
                this.user = user;
                invalidateOptionsMenu();
            }
        });
    }

    public static Intent trainWeathermonMaintenanceIntentFactory(Context context, int loggedInUserId) {
        Intent intent = new Intent(context, TrainWeathermon.class);
        intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, loggedInUserId);
        return intent;
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
        if (user!=null){
            item.setTitle(user.getUsername());
        }
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                showLogoutDialog();
                return false;
            }
        });
        return true;
    }

    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(TrainWeathermon.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Confirm Logout");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                logout();
            }
        });

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                alertDialog.dismiss();
            }
        });
        alertBuilder.create().show();
   }
    private void logout() {
        Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
        startActivity(intent);
    }
}