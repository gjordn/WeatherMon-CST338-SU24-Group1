package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weathermon.database.WeathermonDatabase;

@Entity(tableName = WeathermonDatabase.ABILITY_TABLE)
public class Ability {

    @PrimaryKey(autoGenerate = true)
    private int abilityID;
    private String abilityName;
    private int weatherAttBonus;
    private int coolDownRounds;
    private int attackBaseDamage;

    public Ability(String abilityName, int weatherAttBonus, int coolDownRounds, int attackBaseDamage) {
        this.abilityName = abilityName;
        this.weatherAttBonus = weatherAttBonus;
        this.coolDownRounds = coolDownRounds;
        this.attackBaseDamage = attackBaseDamage;
    }

    public int getAbilityID() {
        return abilityID;
    }

    public void setAbilityID(int abilityID) {
        this.abilityID = abilityID;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public int getWeatherAttBonus() {
        return weatherAttBonus;
    }

    public void setWeatherAttBonus(int weatherAttBonus) {
        this.weatherAttBonus = weatherAttBonus;
    }

    public int getCoolDownRounds() {
        return coolDownRounds;
    }

    public void setCoolDownRounds(int coolDownRounds) {
        this.coolDownRounds = coolDownRounds;
    }

    public int getAttackBaseDamage() {
        return attackBaseDamage;
    }

    public void setAttackBaseDamage(int attackBaseDamage) {
        this.attackBaseDamage = attackBaseDamage;
    }
}
