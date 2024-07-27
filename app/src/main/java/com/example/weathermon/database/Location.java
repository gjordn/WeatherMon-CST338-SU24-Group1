package com.example.weathermon.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "location")
public class Location {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "location")
    private String location;

    private String condition;
    private double temperature;

    // Constructor, getters, and setters
    public Weathermon(String location, String condition, double temperature) {
        this.location = location;
        this.condition = condition;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}

