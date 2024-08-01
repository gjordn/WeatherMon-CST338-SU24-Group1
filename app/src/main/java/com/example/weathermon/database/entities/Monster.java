package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.weathermon.R;
import com.example.weathermon.database.WeathermonDatabase;

import java.util.HashMap;
import java.util.Map;

@Entity(
    tableName = WeathermonDatabase.MONSTER_TABLE,
    foreignKeys = {
        @ForeignKey(entity = Ability.class, parentColumns = "abilityID", childColumns = "ability1"),
        @ForeignKey(entity = Ability.class, parentColumns = "abilityID", childColumns = "ability2"),
        @ForeignKey(entity = Ability.class, parentColumns = "abilityID", childColumns = "ability3")
    },
    indices = {
        @Index(value = "ability1"),
        @Index(value = "ability2"),
        @Index(value = "ability3")
    }
)
public class Monster {
    @PrimaryKey(autoGenerate = true)
    private int monster_id;
    public static final int weatherNone = 1;
    public static final int weatherFire = 2;
    public static final int weatherIce = 3;
    public static final int weatherLight = 4;
    public static final int weatherDark = 5;
    public static final int weatherWind = 6;
    public static final int weatherWater = 7;


    public static final Map<Integer , Integer> convertWeatherTypeToDrawable =
            Map.of(weatherNone, R.drawable.noweatherlogo,
                    weatherFire,R.drawable.firelogo,
                    weatherIce, R.drawable.icelogo,
                    weatherLight, R.drawable.lightlogo,
                    weatherDark, R.drawable.darklogo,
                    weatherWind, R.drawable.windlogo,
                    weatherWater,R.drawable.waterlogo );

    private String monster_name;
    private int ability1;
    private int ability2;
    private int ability3;
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int weatherInnate;

    public Monster(String monster_name, int ability1, int ability2, int ability3, int baseHP, int baseAttack, int baseDefense, int weatherInnate) {
        this.monster_name = monster_name;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.ability3 = ability3;
        this.baseHP = baseHP;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.weatherInnate = weatherInnate;
    }

    public Monster() {

    }

    // Getters and setters

    public int getMonster_id() {
        return monster_id;
    }

    public void setMonster_id(int monster_id) {
        this.monster_id = monster_id;
    }

    public String getMonster_name() {
        return monster_name;
    }

    public void setMonster_name(String monster_name) {
        this.monster_name = monster_name;
    }

    public int getAbility1() {
        return ability1;
    }

    public void setAbility1(int ability1) {
        this.ability1 = ability1;
    }

    public int getAbility2() {
        return ability2;
    }

    public void setAbility2(int ability2) {
        this.ability2 = ability2;
    }

    public int getAbility3() {
        return ability3;
    }

    public void setAbility3(int ability3) {
        this.ability3 = ability3;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public int getWeatherInnate() {
        return weatherInnate;
    }

    public void setWeatherInnate(int weatherInnate) {
        this.weatherInnate = weatherInnate;
    }
}


