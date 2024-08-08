package com.example.weathermon.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import com.example.weathermon.database.dao.CardDAO;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.dao.UserDAO;
import com.example.weathermon.database.dao.MonsterDAO;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.Location;
import com.example.weathermon.database.entities.User;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.database.typeconverters.LocalDateTimeTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTimeTypeConverter.class)
@Database(entities = {User.class, Card.class, Location.class, Monster.class}, version = 4, exportSchema = false)
public abstract class WeathermonDatabase extends RoomDatabase {
    private static final String WEATHERMON_DATABASE_NAME = "WeathermonDatabase";

    // Table Names
    public static final String USER_TABLE = "UserTable";
    public static final String LOCATION_TABLE = "LocationTable";
    public static final String CARD_TABLE = "CardTable";
    public static final String MONSTER_TABLE = "MonsterTable";

    private static final String WEATHERMON_DEFAULT_DATABASE = "database/WeathermonDefaultDatabase.db";
    private static final int NUMBER_OF_THREADS = 3;
    static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public abstract CardDAO cardDAO();
    public abstract LocationDAO locationDao();
    public abstract MonsterDAO monsterDAO();
    public abstract UserDAO userDAO();

    private static volatile WeathermonDatabase INSTANCE;

    public static WeathermonDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (WeathermonDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    WeathermonDatabase.class,
                                    WEATHERMON_DATABASE_NAME)
                                    .fallbackToDestructiveMigration()
                                    .createFromAsset(WEATHERMON_DEFAULT_DATABASE)
//                                    .addCallback(addDefaultValues)
                                    .build();
                }
            }
        }
        return INSTANCE;
    }

    @Deprecated
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);



            databaseWriterExecutor.execute(()-> {
            UserDAO dao = INSTANCE.userDAO();
            dao.deleteALL();
            User admin = new User("admin2", "admin2", true);
            dao.insert(admin);

            User testUser1 = new User("testuser1", "testuser1", false);
            dao.insert(testUser1);
            });
        }
    };

}
