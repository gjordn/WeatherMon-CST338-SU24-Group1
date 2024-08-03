package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.example.weathermon.database.WeathermonDatabase;

import java.time.LocalDateTime;

@Entity(tableName = WeathermonDatabase.LOCATION_TABLE)
public class Location {
    @PrimaryKey(autoGenerate = true)
    private int arenaID;

    @ColumnInfo(name = "location")
    private String location;
    private String arenaName;

    private int windspeed;
    private int humidity;
    private int temperature;
    private boolean realLocation;
    private LocalDateTime localTime;
    private boolean dayOrNight;

    // Constructor, getters, and setters

    public Location(String location, String arenaName, boolean realLocation, boolean dayOrNight) {
        this.location = location;
        this.arenaName = arenaName;
        this.realLocation = realLocation;
        this.dayOrNight =  dayOrNight;
    }

    public int getId() {
        return arenaID;
    }

    public void setId(int id) {
        this.arenaID = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public int getArenaID() {
        return arenaID;
    }

    public void setArenaID(int arenaID) {
        this.arenaID = arenaID;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public int getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(int windspeed) {
        this.windspeed = windspeed;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isRealLocation() {
        return realLocation;
    }

    public void setRealLocation(boolean realLocation) {
        this.realLocation = realLocation;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }

    public boolean isDayOrNight() {
        return dayOrNight;
    }

    public void setDayOrNight(boolean dayOrNight) {
        this.dayOrNight = dayOrNight;
    }
}

