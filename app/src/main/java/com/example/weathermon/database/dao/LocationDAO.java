package com.example.weathermon.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Location;

import java.util.List;

@Dao
public interface LocationDAO {

    @Insert
    void insert(Location... locations);

    @Update
    void update(Location... locations);

    @Delete
    void delete(Location... locations);

    @Query("SELECT * FROM " + WeathermonDatabase.LOCATION_TABLE + " WHERE arenaID = :id")
    Location getLocationByID(int id);

    @Query("SELECT * FROM " + WeathermonDatabase.LOCATION_TABLE)
    List<Location> getAllLocations();
}
