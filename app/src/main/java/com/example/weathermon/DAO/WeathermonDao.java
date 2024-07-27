package com.example.weathermon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.WeatherMon;

import java.util.List;

@Dao
public interface WeatherMonDao {

    @Insert
    void insert(WeatherMon weatherMon);

    @Update
    void update(WeatherMon weatherMon);

    @Delete
    void delete(WeatherMon weatherMon);

    @Query("SELECT * FROM weathermon WHERE id = :id")
    WeatherMon getWeatherMonById(int id);

    @Query("SELECT * FROM weathermon")
    List<WeatherMon> getAllWeatherMons();
}

