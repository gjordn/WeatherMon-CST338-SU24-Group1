package com.example.weathermon.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.weathermon.database.dao.UserDao;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.User;

@Database(entities = {User.class, Location.class}, version = 1)
public abstract class WethermonDatabase extends RoomDatabase {

    private static volatile WethermonDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract LocationDAO locationDao();

    public static WethermonDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (WethermonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WethermonDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

