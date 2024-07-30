package com.example.weathermon.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface MonsterDAO {
    @Insert
    void insert(Monster monster);

    @Update
    void update(Monster monster);

    @Query("SELECT * FROM monster WHERE monster_id = :id")
    Monster getMonsterById(int id);

    @Query("SELECT * FROM monster")
    List<Monster> getAllMonsters();
}

