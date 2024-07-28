package com.example.weathermon.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Ability;

import java.util.List;

@Dao
public interface AbilityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Ability ability);

    @Query("SELECT * FROM " + WeathermonDatabase.ABILITY_TABLE + " ORDER BY abilityName")
    LiveData<List<Ability>> getAllAbilities();

    @Query("SELECT * FROM " + WeathermonDatabase.ABILITY_TABLE + " WHERE abilityID == :abilityID")
    LiveData<List<Ability>> getAbilityByID(int abilityID);
}
