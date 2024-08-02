package com.example.weathermon;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityUserCardMantenanceBinding;
import com.example.weathermon.viewholders.CardMaintenanceAdapter;
import com.example.weathermon.viewholders.CardMaintenanceViewModel;

public class UserCardMantenanceActivity extends AppCompatActivity {
    ActivityUserCardMantenanceBinding binding;
    private WeathermonRepository repository;
    private CardMaintenanceViewModel cardMaintenanceViewModel;

    private int loggedInUserID = 1;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCardMantenanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = WeathermonRepository.getRepository(getApplication());

        loginUser(savedInstanceState);

        cardMaintenanceViewModel = new ViewModelProvider(this).get(CardMaintenanceViewModel.class);

        RecyclerView recyclerView = binding.cardMaintenanceDisplayRecyclerView;

        final CardMaintenanceAdapter adapter = new CardMaintenanceAdapter(new CardMaintenanceAdapter.CardMaintenanceDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cardMaintenanceViewModel.getAllCardsByID(loggedInUserID).observe(this, cardList -> {
            adapter.submitList(cardList);
        });

    }

    private void loginUser(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(WEATHERMON_SHARED_PREF_KEY,
                Context.MODE_PRIVATE);
        loggedInUserID = sharedPreferences.getInt(WEATHERMON_SHARED_PREF_USERID, USER_LOGGED_OUT);

        if (loggedInUserID == USER_LOGGED_OUT){
            //Check intent for logged in user if not found in sharedPreferences.
            loggedInUserID = getIntent().getIntExtra(WEATHERMON_LOGGED_IN_USER_ID, USER_LOGGED_OUT);
        }

        if(loggedInUserID == USER_LOGGED_OUT){
            return;  //No user id, exit out.
        }

        LiveData<User> userObserver = repository.getUserByUserID(loggedInUserID);
        userObserver.observe(this, user -> {
            //Find user, if found, set shared preferences to userID and reset menu options
            if (user != null){
                this.user = user;
                invalidateOptionsMenu();
            }
        });


    }


    /**
     * Code block prepared by Gerek, copy from Main Activity
     */
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
        item.setTitle(user.getUsername());
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
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(UserCardMantenanceActivity.this);
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

    /**
     * End Gerek code block
     */

    static Intent userCardMaintenanceIntentFactory(Context context, int userID){
        Intent intent = new Intent(context, UserCardMantenanceActivity.class);
        intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, userID);
        return intent;
    }

}