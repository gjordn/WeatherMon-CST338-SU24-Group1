package com.example.weathermon.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Monster;

import java.util.List;

@Dao
public interface MonsterDAO {
    @Insert
    void insert(Monster monster);

    @Update
    void update(Monster monster);

    @Query("SELECT * FROM " + WeathermonDatabase.MONSTER_TABLE + " WHERE monster_id = :id")
    Monster getMonsterById(int id);

    @Query("SELECT * FROM " + WeathermonDatabase.MONSTER_TABLE)
    List<Monster> getAllMonsters();

    @Query("SELECT * FROM " + WeathermonDatabase.MONSTER_TABLE + " ORDER BY RANDOM() LIMIT 1")
    LiveData<Monster> getRandomMonster();
}


