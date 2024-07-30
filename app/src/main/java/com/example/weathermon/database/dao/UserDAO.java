package com.example.weathermon.database.dao;

import androidx.lifecycle.LiveData;
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
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM "+ WeathermonDatabase.USER_TABLE)
    void deleteALL();

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE id = :id")
    LiveData<User> getUserById(int id);

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE isAdmin = 1")
    List<User> getAllAdmins();

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE username = :username AND password = :password")
    User getUserByUsernameAndPassword(String username, String password);

    @Query("SELECT * FROM " + WeathermonDatabase.USER_TABLE + " WHERE username = :username")
    User getUserByUsername(String username);
}
