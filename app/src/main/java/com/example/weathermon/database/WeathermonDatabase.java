package com.example.weathermon.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.weathermon.database.dao.CardDAO;
import com.example.weathermon.database.dao.UserDao;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.User;

@Database(entities = {User.class, Location.class, Card.class}, version = 1, exportSchema = false)
public abstract class WeathermonDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "GymLogdatabase";
    //Table Names
    public static final String USER_TABLE = "UserTable";
    public static final String LOCATION_TABLE = "LocationTable";
    public static final String CARD_TABLE = "CardTable";



    private static volatile WeathermonDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract LocationDAO locationDao();
    public abstract CardDAO card();

    public static WeathermonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WeathermonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WeathermonDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

