package com.example.weathermon;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(AndroidJUnit4.class)

public class LocationTest {
    private static final String REPLACEMENT_ARENA_NAME = "Red Winds Arena";
    private WeathermonDatabase db;
    private static User testUser1;
    private static Location testLocation1;
    private static Monster testMonster1;
    private static Card testCard1;
    private static final int FIRST_ID_NUMBER = 1;
    private static final int BASE_HP = 100;
    private static final int BASE_ATTACK = 200;
    private static final int BASE_DEFENCE = 300;
    private static final boolean IS_ADMIN = false;
    private static final int WEATHER_INNATE_HOT_BONUS = 1;
    private static final String CITY_NAME = "Detroit";
    private static final String ARENA_NAME = "Motown Arena";
    private static final boolean REAL_LOCATION = true;
    private static final boolean IS_DAYTIME =true;
    private static final int NEW_XP = 400;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, WeathermonDatabase.class).build();

        testCard1 = new Card(FIRST_ID_NUMBER, FIRST_ID_NUMBER);
        testCard1.setCardCustomName("CustomCardName");
        testCard1.setCardID(FIRST_ID_NUMBER);

        testUser1 = new User("ATestUser","PASSWORD", IS_ADMIN);
        testUser1.setId(FIRST_ID_NUMBER);

        testMonster1 = new Monster("TestMonster", BASE_HP, BASE_ATTACK, BASE_DEFENCE, WEATHER_INNATE_HOT_BONUS);
        testMonster1.setMonster_id(FIRST_ID_NUMBER);

        testLocation1 = new Location(CITY_NAME,ARENA_NAME,REAL_LOCATION,IS_DAYTIME);
        testLocation1.setLocalTime(LocalDateTime.now());
        testLocation1.setArenaID(FIRST_ID_NUMBER);

        db.userDAO().insert(testUser1);
        db.monsterDAO().insert(testMonster1);
        db.cardDAO().insert(testCard1);
        db.locationDao().insert(testLocation1);

    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void ReadInList() throws Exception {
        Location returnCards = db.locationDao().getLocationByID(testLocation1.getArenaID());
        assertThat(returnCards, equalTo(testLocation1));
    }

    @Test
    public void UpdateCardAndReadInList() throws Exception {
        Location returnLocations = db.locationDao().getLocationByID(testLocation1.getArenaID());
        assertThat(returnLocations, equalTo(testLocation1));

        testLocation1.setArenaName(REPLACEMENT_ARENA_NAME);
        db.locationDao().update(testLocation1);

        returnLocations = db.locationDao().getLocationByID(testLocation1.getArenaID());
        assertThat(returnLocations, equalTo(testLocation1));

    }

    @Test
    public void DeleteCardAndReadInList() {
        Location returnLocations = db.locationDao().getLocationByID(testLocation1.getArenaID());
        assertThat(returnLocations, equalTo(testLocation1));

        db.locationDao().delete(testLocation1);

        returnLocations = db.locationDao().getLocationByID(testLocation1.getArenaID());
        assertNull(returnLocations);

    }
}
