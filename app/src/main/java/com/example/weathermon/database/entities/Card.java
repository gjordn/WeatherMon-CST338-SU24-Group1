package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weathermon.database.WeathermonDatabase;

import java.util.Objects;

@Entity(tableName = WeathermonDatabase.CARD_TABLE)
public class Card {
    private static final int MONSTER_STARTING_XP = 0;
    private static final int MONSTER_STARTTING_LEVEL = 1;
    private static final double MONSTER_LEVEL_EXPONETIAL = 2;
    private static final double BASE_PER_LEVEL = 5;

    @PrimaryKey(autoGenerate = true)
    private int cardID;
    private int monsterID;
    private int monsterXP;
    private int userID;
    private int monsterLevel;

    public Card(int monsterID, int userID){
        this.monsterID = monsterID;
        this.userID = userID;
        monsterXP = MONSTER_STARTING_XP;
        monsterLevel = MONSTER_STARTTING_LEVEL;
    }

    /**
     * generated getters and setters
     *
     */
    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public int getMonsterXP() {
        return monsterXP;
    }

    /**
     * Update the level of a monster at the same time XP is changed.
     * @param monsterXP
     */
    public void setMonsterXP(int monsterXP) {
        this.monsterXP = monsterXP;
        updateLevel();
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMonsterLevel() {
        return monsterLevel;
    }

    public void setMonsterLevel(int monsterLevel) {
        this.monsterLevel = monsterLevel;
    }

    /**
     * Generated equals and hash
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardID == card.cardID && monsterID == card.monsterID && monsterXP == card.monsterXP && userID == card.userID && monsterLevel == card.monsterLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardID, monsterID, monsterXP, userID, monsterLevel);
    }

    /**
     * Checks XP amount and adjust monster level as needed
     */
    private void updateLevel(){
        monsterLevel = MONSTER_STARTTING_LEVEL + (int) (monsterXP/(Math.pow(MONSTER_LEVEL_EXPONETIAL, monsterLevel)*BASE_PER_LEVEL));
    }

    /**
     * checks XP and Level and returns the XP earned towards the next level
     * @return XP past current level
     */
    public int getXPToNextLevel(){
        //TODO: Write code to return XP as an amount to next level.
        return 0;
    }

    /**
     * checks XP and level and returns the amount of XP needed to reach the next level
     * @return XP from current level to next level.
     */
    public int getXPNeedForNextLevel() {
        //TODO: Write code to return the total number of xp needed for the next level;
        return 0;
    }
}


