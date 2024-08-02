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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityMainBinding;

import fragments.MainPageAdminButton;

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
                Intent intent = UserCardMantenanceActivity.userCardMaintenanceIntentFactory(getApplicationContext(), loggedInUserId);
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
                if (user.isAdmin()){
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fragment_admin_button_container, MainPageAdminButton.class, null)
                            .commit();
                }
                Log.d(TAG, "User data loaded: " + user.getUsername());
                invalidateOptionsMenu();
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
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                showLogoutDialog();
                return false;
            }
        });
        return true;
    }

    private void showLogoutDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
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