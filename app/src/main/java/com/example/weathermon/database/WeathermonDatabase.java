package com.example.weathermon.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weathermon.database.dao.AbilityDAO;
import com.example.weathermon.database.dao.CardDAO;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.dao.UserDao;
import com.example.weathermon.database.dao.MonsterDAO;
import com.example.weathermon.database.entities.Ability;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.database.entities.Monster;

@Database(entities = {User.class, Ability.class, Card.class, Location.class, Monster.class}, version = 1, exportSchema = false)
public abstract class WeathermonDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";

    // Table Names
    public static final String USER_TABLE = "UserTable";
    public static final String LOCATION_TABLE = "LocationTable";
    public static final String CARD_TABLE = "CardTable";
    public static final String ABILITY_TABLE = "AbilityTable";
    public static final String MONSTER_TABLE = "MonsterTable"; // Add this line

    private static volatile WeathermonDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract AbilityDAO abilityDAO();
    public abstract CardDAO cardDAO();
    public abstract LocationDAO locationDAO();
    public abstract MonsterDAO monsterDAO();

    public static WeathermonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WeathermonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WeathermonDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
