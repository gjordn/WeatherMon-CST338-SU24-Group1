package com.example.weathermon.database.entities;

import androidx.room.PrimaryKey;

import com.example.weathermon.R;

import java.util.Map;

public class Monster {
    public static final Double innateWeatherBonus = 1.5;
    @PrimaryKey(autoGenerate = true)
    private int monster_id;
    public static final Double levelModifier = 1.2; // Increase in power per level
    public static final int weatherNone = 1;
    public static final int weatherFire = 2;
    public static final int weatherIce = 3;
    public static final int weatherLight = 4;
    public static final int weatherDark = 5;
    public static final int weatherWind = 6;
    public static final int weatherWater = 7;

    public static final Map<Integer, Integer> convertWeatherTypeToDrawable =
            Map.of(weatherNone, R.drawable.noweatherlogo,
                    weatherFire, R.drawable.firelogo,
                    weatherIce, R.drawable.icelogo,
                    weatherLight, R.drawable.lightlogo,
                    weatherDark, R.drawable.darklogo,
                    weatherWind, R.drawable.windlogo,
                    weatherWater, R.drawable.waterlogo);

    public static final Map<Integer, String> convertWeatherTypeToString =
            Map.of(weatherNone, "None",
                    weatherFire, "Fire",
                    weatherIce, "Ice",
                    weatherLight, "Light",
                    weatherDark, "Shadow",
                    weatherWind, "Wind",
                    weatherWater, "Water");

    private String name;
    private int weatherType;

    // Getters and Setters
    public int getMonster_id() {
        return monster_id;
    }

    public void setMonster_id(int monster_id) {
        this.monster_id = monster_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(int weatherType) {
        this.weatherType = weatherType;
    }
}



