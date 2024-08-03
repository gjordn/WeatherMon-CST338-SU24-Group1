package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.weathermon.database.WeathermonDatabase;

import java.util.Objects;

@Entity(
        tableName = WeathermonDatabase.CARD_TABLE,
        foreignKeys = {
        @ForeignKey(
                entity = Monster.class,
                parentColumns = {"monster_id"},
                childColumns = {"monsterID"},
                onDelete = ForeignKey.NO_ACTION,
                onUpdate = ForeignKey.NO_ACTION
        )
    }, indices = {@Index(value = {"monsterID"})}
)

public class Card {
    private static final int MONSTER_STARTING_XP = 0;
    private static final int baseXPForLevel = 10;
    private static final double levelExponential = 2;

    @PrimaryKey(autoGenerate = true)
    private int cardID;
    private String cardCustomName;
    private int monsterID;
    private int monsterXP;
    private int userID;

    public Card(int monsterID, int userID){
        this.monsterID = monsterID;
        this.userID = userID;
        monsterXP = MONSTER_STARTING_XP;
        cardCustomName = "";
    }

    public static int getXPToNextLevel(int monsterXP) {
        double neededXP;
        neededXP = baseXPForLevel*(Math.pow(levelExponential, getCurrentLevel(monsterXP)-1));
        return (int) neededXP;
    }

    //There is 100% a better way to do this, I don't know how.
    public static int getCurrentLevel(int monsterXP) {
        int currentLevel =0;
        while (monsterXP >= baseXPForLevel*(Math.pow(levelExponential, currentLevel))){
            currentLevel++;
        }
        currentLevel++;
        return currentLevel;
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
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCardCustomName() {
        return cardCustomName;
    }

    public void setCardCustomName(String cardCustomName) {
        this.cardCustomName = cardCustomName;
    }

    /**
     * Generated equals and hash
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardID == card.cardID && monsterID == card.monsterID && monsterXP == card.monsterXP && userID == card.userID && Objects.equals(cardCustomName, card.cardCustomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardID, cardCustomName, monsterID, monsterXP, userID);
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


