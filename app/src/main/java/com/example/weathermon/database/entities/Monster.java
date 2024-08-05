package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.weathermon.R;
import com.example.weathermon.database.WeathermonDatabase;

import java.util.Map;

@Entity(
    tableName = WeathermonDatabase.MONSTER_TABLE
)

public class Monster {
    public static final Double innateWeatherBonus = 1.5;
    @PrimaryKey(autoGenerate = true)
    private int monster_id;
    public static final Double levelModifier = 1.2; //Increase in power per level
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

    public static final Map<Integer , String> convertWeatherTypeToString =
            Map.of(weatherNone, "None",
                    weatherFire, "Fire",
                    weatherIce, "Ice",
                    weatherLight, "Light",
                    weatherDark, "Shadow",
                    weatherWind, "Wind",
                    weatherWater, "Water");

    private String monster_name;
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int weatherInnate;

    public Monster(String monster_name, int baseHP, int baseAttack, int baseDefense, int weatherInnate) {
        this.monster_name = monster_name;
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


