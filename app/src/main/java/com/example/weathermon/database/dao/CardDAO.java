package com.example.weathermon.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.WeathermonDatabase;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Card... cards);

    @Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " ORDER BY monsterXP DESC")
    LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " WHERE userID == :userID ORDER BY monsterXP DESC")
    LiveData<List<Card>> getCardsByUserID(int userID);

@Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " WHERE userID == :userID ORDER BY monsterXP DESC")
    List<Card> getCardsByUserIDTest(int userID);

    @Delete
    void delete(Card...cards);


    @Query("DELETE FROM " + WeathermonDatabase.CARD_TABLE + " WHERE cardID==:cardID")
    void deleteCardByID(int cardID);

    @Update
    void updateCards(Card... cards);


    @Query("SELECT * FROM " + WeathermonDatabase.CARD_TABLE + " INNER JOIN " +
            WeathermonDatabase.MONSTER_TABLE + " ON " +
            WeathermonDatabase.CARD_TABLE + ".monsterID=" + WeathermonDatabase.MONSTER_TABLE +
            ".monster_id WHERE userID == :userID ORDER BY monsterXP DESC")
    LiveData<List<CardWithMonster>> getCardsWithMonsterByUserID(int userID);

    @Query("UPDATE " + WeathermonDatabase.CARD_TABLE + " SET monsterXP = :newXP WHERE cardID==:cardID")
    void updateCardXPByCardID(int cardID, int newXP);
}
