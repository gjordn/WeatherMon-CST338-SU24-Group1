package com.example.weathermon.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapp.database.dao.AbilityDAO;
import com.example.myapp.database.dao.CardDAO;
import com.example.myapp.database.dao.LocationDAO;
import com.example.myapp.database.dao.UserDao;
import com.example.myapp.database.dao.MonsterDAO; // Ensure this import exists
import com.example.myapp.database.entities.Ability;
import com.example.myapp.database.entities.Card;
import com.example.myapp.database.entities.Location;
import com.example.myapp.database.entities.User;
import com.example.myapp.database.entities.Monster; // Ensure this import exists

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
