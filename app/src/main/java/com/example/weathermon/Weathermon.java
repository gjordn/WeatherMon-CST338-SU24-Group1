package com.example.weathermon;

public class Weathermon {
    private String name;
    private String weatherType;
    private int weatherTypeInt;
    private int imageResource;
    private boolean isCaught;
    private int monster_id;

    public Weathermon() {
    }

    public Weathermon(String name, String weatherType, int imageResource, boolean isCaught) {
        this.name = name;
        this.weatherType = weatherType;
        this.imageResource = imageResource;
        this.isCaught = isCaught;
    }

    public int getWeatherTypeInt() {
        return weatherTypeInt;
    }

    public void setWeatherTypeInt(int weatherTypeInt) {
        this.weatherTypeInt = weatherTypeInt;
    }

    public String getName() {
        return name;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getMonster_id() {
        return monster_id;
    }

    public void setMonster_id(int monster_id) {
        this.monster_id = monster_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public void setCaught(boolean caught) {
        isCaught = caught;
    }

    public boolean isCaught() {
        return isCaught;
    }
}


