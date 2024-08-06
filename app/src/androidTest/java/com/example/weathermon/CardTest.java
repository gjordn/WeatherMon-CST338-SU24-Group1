package com.example.weathermon;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.dao.CardDAO;
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

public class CardTest {
        private CardDAO cardDAO;
        private WeathermonDatabase db;
        private static User testUser1;
        private static Location testLocation1;
        private static Monster testMonster1;
        private static Card testCard1;
        private static final int firstIDNumber = 1;
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

            testCard1 = new Card(firstIDNumber,firstIDNumber);
            testCard1.setCardCustomName("CustomCardName");
            testCard1.setCardID(firstIDNumber);

            testUser1 = new User("ATestUser","PASSWORD", IS_ADMIN);
            testUser1.setId(firstIDNumber);

            testMonster1 = new Monster("TestMonster", BASE_HP, BASE_ATTACK, BASE_DEFENCE, WEATHER_INNATE_HOT_BONUS);
            testMonster1.setMonster_id(firstIDNumber);

            testLocation1 = new Location(CITY_NAME,ARENA_NAME,REAL_LOCATION,IS_DAYTIME);
            testLocation1.setLocalTime(LocalDateTime.now());
            testLocation1.setId(firstIDNumber);

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
        List<Card> returnCards = db.cardDAO().getCardsByUserIDTest(testCard1.getCardID());
        assertThat(returnCards.get(0), equalTo(testCard1));
    }

    @Test
    public void UpdateCardAndReadInList() throws Exception {
        List<Card> returnCards = db.cardDAO().getCardsByUserIDTest(testCard1.getCardID());
        assertThat(returnCards.get(0), equalTo(testCard1));

        testCard1.setMonsterXP(NEW_XP);
        db.cardDAO().updateCards(testCard1);

        returnCards = db.cardDAO().getCardsByUserIDTest(testCard1.getCardID());
        assertThat(returnCards.get(0), equalTo(testCard1));

    }

    @Test
    public void DeleteCardAndReadInList() {
        List<Card> returnCards = db.cardDAO().getCardsByUserIDTest(testCard1.getCardID());
        assertThat(returnCards.get(0), equalTo(testCard1));

        db.cardDAO().delete(testCard1);

        returnCards = db.cardDAO().getCardsByUserIDTest(testCard1.getCardID());
        assertTrue(returnCards.isEmpty());

    }
}
