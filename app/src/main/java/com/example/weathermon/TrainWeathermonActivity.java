package com.example.weathermon;

import static com.example.weathermon.api.WeatherstackInterface.BASE_URL;
import static com.example.weathermon.api.WeatherstackInterface.CURRENT_LOCATION_BY_IP;
import static com.example.weathermon.api.WeatherstackInterface.ENGLISH_UNITS;
import static com.example.weathermon.database.Util.USER_LOGGED_OUT;
import static com.example.weathermon.database.Util.WEATHERMON_LOGGED_IN_USER_ID;
import static com.example.weathermon.database.Util.WEATHERMON_SHARED_PREF_KEY;
import static com.example.weathermon.database.Util.WEATHERMON_SHARED_PREF_USERID;

import static fragments.ResultsFragment.HERO_WON;
import static fragments.ResultsFragment.VILLAIN_WON;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.weathermon.api.WeatherstackInterface;
import com.example.weathermon.api.WeatherstackWeatherHolder;
import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityTrainWeathermonBinding;

import fragments.BattleFightButtonFragment;
import fragments.BattleFragment;
import fragments.BattleNextButtonFragment;
import fragments.BattleTravelButtonFragment;
import fragments.LocationSelectionFragment;
import fragments.MainPageAdminButton;
import fragments.ResultsFragment;
import fragments.SelectCardFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrainWeathermonActivity extends AppCompatActivity {
    ActivityTrainWeathermonBinding binding;
    private WeathermonRepository repository;
    private int loggedInUserID = 1;
    private User user;
    private Location trainingLocation;
    private CardWithMonster cardToTrain;
    private CardWithMonster cardToBattle;

    private Retrofit retrofit;
    private int view;
    private LocationSelectionFragment locationSelectionFragment;
    private BattleFightButtonFragment battleFightButtonFragment;
    private BattleNextButtonFragment battleNextButtonFragment;
    private BattleTravelButtonFragment battleTravelButtonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrainWeathermonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = WeathermonRepository.getRepository(getApplication());
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        locationSelectionFragment =new LocationSelectionFragment();
        battleFightButtonFragment = new BattleFightButtonFragment();
        battleNextButtonFragment = new BattleNextButtonFragment();
        battleTravelButtonFragment = new BattleTravelButtonFragment();


        loginUser(savedInstanceState);
        setLocationHome();//Go to "Home" arena
        CardWithMonster.setBattleLocation(trainingLocation);//Set location of battle.

        updateRealLocation(trainingLocation.getLocation());

        binding.buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUserID);
                startActivity(intent);
            }
        });


    }


    private void setLocationHome() {
        trainingLocation = new Location(CURRENT_LOCATION_BY_IP, " ",true,true);

    }

    private void updateRealLocation(String proposedLocation) {
        WeatherstackInterface weatherstackInterface = retrofit.create(WeatherstackInterface.class);
        weatherstackInterface.getWeartherstackWeather(proposedLocation, ENGLISH_UNITS).enqueue(new Callback<WeatherstackWeatherHolder>() {
            @Override
            public void onResponse(@NonNull Call<WeatherstackWeatherHolder> call, @NonNull Response<WeatherstackWeatherHolder> response) {
                assert response.body() != null;
                if (response.body().success==null) { //Success if null unless the pull of data fails.
                    trainingLocation.setLocation(proposedLocation);
                    trainingLocation.setArenaName(response.body().location.name);
                    trainingLocation.setTemperature(response.body().getConvertedTemperature());
                    trainingLocation.setHumidity(response.body().getConvertedHumidity());
                    trainingLocation.setWindspeed(response.body().getConvertedWindspeed());
                    trainingLocation.setIsDaytime(response.body().getConvertedIsDaytime());
                    trainingLocation.setLocalTime(response.body().getConvertedDateTime());

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_train_container, LocationSelectionFragment.class, null)
                            .commit();
                    //Only add buttons if found location.
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.battleMiddleButton, battleTravelButtonFragment, null)
                            .commit();

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.battleRightButton, battleNextButtonFragment, null)
                            .commit();


                }else {
                    Toast.makeText(getApplicationContext(), "Sorry, we couldn't find that city", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(Call<WeatherstackWeatherHolder> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Sorry, we couldn't find that city", Toast.LENGTH_LONG).show();
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

    public Location getLocationInfo(){
        return trainingLocation;
    }


    public static Intent trainWeathermonMaintenanceIntentFactory(Context context, int loggedInUserId) {
        Intent intent = new Intent(context, TrainWeathermonActivity.class);
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
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(TrainWeathermonActivity.this);
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

    public int getUserID() {
        return user.getId();
    }

    public void setCardToTrain(CardWithMonster cardSelected) {
        cardToTrain = cardSelected;

        LiveData<Monster> monsterObserver = repository.getRandomMonster();
        monsterObserver.observe(this, monster -> {
            if (monster != null) {
                cardToBattle = CardWithMonster.getTrainingOpponent(cardToTrain.getMonsterXP(), monster);
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_train_container, BattleFragment.class, null)
                        .commit();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.battleRightButton,battleFightButtonFragment, null)
                        .commit();
            }
        });
    }

    public CardWithMonster getHero() {
        return cardToTrain;
    }

    public CardWithMonster getVillan() {
        return cardToBattle;
    }

    public void runResultsFragment() {
        Boolean didWeWin = cardToTrain.fight(cardToBattle);
        String winner;
        if (didWeWin ){
            winner=HERO_WON;
        } else {
            winner=VILLAIN_WON;
        }
        ResultsFragment resultsFragment = ResultsFragment.newInstance(winner);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_train_container, resultsFragment, null)
                .commit();

    }

    public void buttonNewLocation() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(TrainWeathermonActivity.this);
        final EditText input = new EditText(TrainWeathermonActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
        alertBuilder.setView(input);

        alertBuilder.setTitle("Travel the World!");


            alertBuilder.setMessage("Enter the name of the city you would like to travel to");

        alertBuilder.setPositiveButton("Travel to a new arena", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                updateRealLocation(input.getText().toString());
            }
        });

        alertBuilder.setNegativeButton("Stay here for now.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.create().show();
    }

    public void buttonNextPage() {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_train_container, SelectCardFragment.class, null)
                    .commit();
        getSupportFragmentManager().beginTransaction()
                .remove(battleTravelButtonFragment)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .remove(battleNextButtonFragment)
                .commit();

    }
}