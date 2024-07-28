package com.example.weathermon.database.dao;

import android.app.SearchManager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE id = :id")
    User getUserById(int id);

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE isAdmin = 1")
    List<User> getAllAdmins();
}
