package com.example.weathermon.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weathermon.database.entities.Card;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Card card);

    @Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " ORDER BY monsterXP DESC")
    LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " WHERE userID == :userID ORDER BY monsterXP DESC")
    LiveData<List<Card>> getCardsByUserID(int userID);
}
