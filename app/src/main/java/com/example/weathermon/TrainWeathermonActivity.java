package com.example.weathermon;

import static com.example.weathermon.api.WeatherStackInterface.BASE_URL;
import static com.example.weathermon.api.WeatherStackInterface.CURRENT_LOCATION_BY_IP;
import static com.example.weathermon.api.WeatherStackInterface.ENGLISH_UNITS;
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

import com.example.weathermon.api.WeatherStackInterface;
import com.example.weathermon.api.WeatherstackWeatherHolder;
import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.databinding.ActivityTrainWeathermonBinding;

import fragments.BattleAgainFragment;
import fragments.BattleFightButtonFragment;
import fragments.BattleFragment;
import fragments.BattleNextButtonFragment;
import fragments.BattleTravelButtonFragment;
import fragments.LocationSelectionFragment;
import fragments.ResultsFragment;
import fragments.SelectCardFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrainWeathermonActivity extends AppCompatActivity {
    public static final String BATTLE_TYPE = "com.example.weathermon.battle_TYPE";
    public static final String CAMPAIGN_STOP_NUMBER = "com.example.weathermon.STOPNUMBER";
    public static final int BATTLE_TYPE_CAMPAIGN = 0;
    public static final int BATTLE_TYPE_TRAINING = 1;
    public static final int NOT_CAMPAIGN_MODE = -1;

    ActivityTrainWeathermonBinding binding;
    private WeathermonRepository repository;
    private int loggedInUserID = 1;
    private int battleType = 1;
    private int campaignStopNumber;
    private User user;
    private Location battleLocation;
    private CardWithMonster cardToTrain;
    private CardWithMonster cardToBattle;


    private Retrofit retrofit;
    private BattleFightButtonFragment battleFightButtonFragment;
    private BattleNextButtonFragment battleNextButtonFragment;
    private BattleTravelButtonFragment battleTravelButtonFragment;
    private BattleAgainFragment battleAgainFragment;

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

        battleFightButtonFragment = new BattleFightButtonFragment();
        battleNextButtonFragment = new BattleNextButtonFragment();
        battleTravelButtonFragment = new BattleTravelButtonFragment();
        battleAgainFragment = new BattleAgainFragment();

        loginUser(savedInstanceState);

        setUpBattleLocation();

        binding.buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), loggedInUserID);
                startActivity(intent);
            }
        });


    }

    public void setUpBattleLocation() {
        if (battleType==BATTLE_TYPE_TRAINING) {
            setLocationHome();//Go to "Home" arena
        } else {
            setCampaignLocation();
        }
    }

    private void setCampaignLocation() {
        LiveData<Location> locationObserver = repository.getLocationByUserCampaignProgress(campaignStopNumber);
        locationObserver.observe(this, location -> {
            //Find user, if found, set shared preferences to userID and reset menu options
            if (location != null) {
                this.battleLocation = location;
                CardWithMonster.setBattleLocation(battleLocation);//Set location of battle.
                updateLocation();
            }
        });
    }


    private void setLocationHome() {
        battleLocation = new Location(CURRENT_LOCATION_BY_IP, " ",true,true);
        updateLocation();
    }

    private void updateRealLocation(String proposedLocation) {
        WeatherStackInterface weatherstackInterface = retrofit.create(WeatherStackInterface.class);
        weatherstackInterface.getWeatherStackWeather(proposedLocation, ENGLISH_UNITS).enqueue(new Callback<WeatherstackWeatherHolder>() {
            @Override
            public void onResponse(@NonNull Call<WeatherstackWeatherHolder> call, @NonNull Response<WeatherstackWeatherHolder> response) {
                assert response.body() != null;
                if (response.body().success==null) { //Success if null unless the pull of data fails.
                    battleLocation.setLocation(proposedLocation);
                    battleLocation.setArenaName(response.body().location.name);
                    battleLocation.setTemperature(response.body().getConvertedTemperature());
                    battleLocation.setHumidity(response.body().getConvertedHumidity());
                    battleLocation.setWindspeed(response.body().getConvertedWindspeed());
                    battleLocation.setIsDaytime(response.body().getConvertedIsDaytime());
                    battleLocation.setLocalTime(response.body().getConvertedDateTime());

                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_train_container, LocationSelectionFragment.class, null)
                            .commit();
                    //Only add buttons if found location.  Only add button if training
                    if (battleType==BATTLE_TYPE_TRAINING) {
                        getSupportFragmentManager().beginTransaction()
                                .setReorderingAllowed(true)
                                .replace(R.id.battleMiddleButton, battleTravelButtonFragment, null)
                                .commit();
                    }
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.battleRightButton, battleNextButtonFragment, null)
                            .commit();

                CardWithMonster.setBattleLocation(battleLocation);//Set location of battle.

                }else {
                    Toast.makeText(getApplicationContext(), "Sorry, we couldn't find that city", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherstackWeatherHolder> call, @NonNull Throwable throwable) {
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

        battleType = getIntent().getIntExtra(BATTLE_TYPE, BATTLE_TYPE_TRAINING);
        campaignStopNumber = getIntent().getIntExtra(CAMPAIGN_STOP_NUMBER, NOT_CAMPAIGN_MODE);

        if (loggedInUserID == USER_LOGGED_OUT) {
            logout();
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
        return battleLocation;
    }


    public static Intent trainWeathermonMaintenanceIntentFactory(Context context, int loggedInUserId, int battleType) {
        Intent intent = new Intent(context, TrainWeathermonActivity.class);
        intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, loggedInUserId);
        intent.putExtra(BATTLE_TYPE, battleType);
        return intent;
    }

    public static Intent campaignWeathermonMaintenanceIntentFactory(Context context, int loggedInUserId, int battleType, int stopNumber) {
        Intent intent = new Intent(context, TrainWeathermonActivity.class);
        intent.putExtra(WEATHERMON_LOGGED_IN_USER_ID, loggedInUserId);
        intent.putExtra(BATTLE_TYPE, battleType);
        intent.putExtra(CAMPAIGN_STOP_NUMBER, stopNumber);
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

    public CardWithMonster getVillain() {
        return cardToBattle;
    }

    public void runResultsFragment() {
        Boolean didWeWin = cardToTrain.fight(cardToBattle);
        String winner;
        if (didWeWin ){
            int newXP = cardToTrain.getMonsterXP()+cardToBattle.getXPValue();
            cardToTrain.setMonsterXP(newXP);
            repository.updateCardXPByCardID(cardToTrain.getCardID(),cardToTrain.getMonsterXP());
            campaignStopNumber++;
            user.setCampaignProgress(campaignStopNumber);
            repository.updateUser(user);

            winner=HERO_WON;
        } else {
            winner=VILLAIN_WON;
        }
        ResultsFragment resultsFragment = ResultsFragment.newInstance(winner);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_train_container, resultsFragment, null)
                .commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.battleRightButton,battleAgainFragment , null)
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
    public void updateLocation(){
        if(battleLocation.isRealLocation()){
            updateRealLocation(battleLocation.getLocation());
        } else {
            updateFakeLocation();
        }
    }

    private void updateFakeLocation() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_train_container, LocationSelectionFragment.class, null)
                .commit();
        //Only add buttons if found location.  Only add button if training, not travel for campaign mode
        if (battleType==BATTLE_TYPE_TRAINING) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.battleMiddleButton, battleTravelButtonFragment, null)
                    .commit();
        }

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.battleRightButton, battleNextButtonFragment, null)
                .commit();

        CardWithMonster.setBattleLocation(battleLocation);//Set location of battle.

    }
}