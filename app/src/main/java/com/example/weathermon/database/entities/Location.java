package com.example.weathermon.database.entities;

import static com.example.weathermon.database.entities.Monster.weatherDark;
import static com.example.weathermon.database.entities.Monster.weatherFire;
import static com.example.weathermon.database.entities.Monster.weatherIce;
import static com.example.weathermon.database.entities.Monster.weatherLight;
import static com.example.weathermon.database.entities.Monster.weatherNone;
import static com.example.weathermon.database.entities.Monster.weatherWater;
import static com.example.weathermon.database.entities.Monster.weatherWind;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.example.weathermon.R;
import com.example.weathermon.database.WeathermonDatabase;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Entity(tableName = WeathermonDatabase.LOCATION_TABLE)
public class Location {
    private static final int BONUS_TEMP_LIMIT = 70;
    private static final int BONUS_WIND_LIMIT = 10;
    private static final int HUMIDITY_BONUS_LIMIT = 45;

    public static final int hotBonus = 1;
    public static final int iceBonus = 2;
    public static final int lightBonus = 3;
    public static final int darkBonus = 4;
    public static final int windBonus = 5;
    public static final int noWindBonus = 6;
    public static final int humidBonus = 7;
    public static final int noHumidBonus = 8;


    public static final Map<Integer , Integer> bonusToImage =
            Map.of(hotBonus, R.drawable.temphot,
                    iceBonus,R.drawable.tempcold,
                    lightBonus, R.drawable.daytimebonus,
                    darkBonus, R.drawable.nightimebonus,
                    windBonus, R.drawable.windybonus,
                    noWindBonus, R.drawable.nowindbonus,
                    humidBonus, R.drawable.humiditybonus,
                    noHumidBonus,R.drawable.drynobonus );


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
    private boolean isDaytime;
    private int campaignLocationStopNumber;

    // Constructor, getters, and setters

    public Location(String location, String arenaName, boolean realLocation, boolean isDaytime) {
        this.location = location;
        this.arenaName = arenaName;
        this.realLocation = realLocation;
        this.isDaytime = isDaytime;
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

    public boolean isDaytime() {
        return isDaytime;
    }

    public void setIsDaytime(boolean daytime) {
        this.isDaytime = daytime;
    }

    public void setDaytime(boolean daytime) {
        isDaytime = daytime;
    }

    public int getCampaignLocationStopNumber() {
        return campaignLocationStopNumber;
    }

    public void setCampaignLocationStopNumber(int campaignLocationStopNumber) {
        this.campaignLocationStopNumber = campaignLocationStopNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return arenaID == location1.arenaID && windspeed == location1.windspeed && humidity == location1.humidity && temperature == location1.temperature && realLocation == location1.realLocation && isDaytime == location1.isDaytime && campaignLocationStopNumber == location1.campaignLocationStopNumber && Objects.equals(location, location1.location) && Objects.equals(arenaName, location1.arenaName) && Objects.equals(localTime, location1.localTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arenaID, location, arenaName, windspeed, humidity, temperature, realLocation, localTime, isDaytime, campaignLocationStopNumber);
    }

    public String getArenaNameToString() {
        return this.arenaName +" Arena!";
    }

    public String getTemperatureToString() {
        if (bonusToHot()){
            return this.temperature + " °F, Smoking! ";
        }
        return this.temperature + " °F, need long underwear! ";
    }

    private boolean bonusToHot() {
        return (temperature>BONUS_TEMP_LIMIT);
    }

    public String getDayOrNightToString() {
        if (this.isDaytime){
            return "It is daytime, where are my shades?";
        }
        return "It is nightime, the Shadows shift and creep!";
    }

    public String getWindspeedToString() {
        if (bonusToWind()){
            return windspeed + " mph, better hold on!";
        }
        return windspeed + " mph, still as the dead";
    }

    private boolean bonusToWind() {
        return (windspeed > BONUS_WIND_LIMIT);
    }

    public String getHumidityToString() {
        if (bonusToHumidity()){
            return humidity + "%, forget walking, we need a boat";
        }
        return humidity + "%, nice and dry";
    }

    private boolean bonusToHumidity() {
        return (humidity > HUMIDITY_BONUS_LIMIT);
    }


    public int getHumidityBoostImage() {
        if (bonusToHumidity()){
            return bonusToImage.get(humidBonus);
        }
        return bonusToImage.get(noHumidBonus);
    }

    public int getWindBoostImage() {
        if (bonusToWind()){
            return bonusToImage.get(windBonus);
        }
        return bonusToImage.get(noWindBonus);
    }

    public int getIsDayBoostImage() {
        if (isDaytime()){
            return bonusToImage.get(lightBonus);
        }
        return bonusToImage.get(darkBonus);
    }

    public int getTempBoostImage() {
        if (bonusToHot()){
            return bonusToImage.get(hotBonus);
        }
        return bonusToImage.get(iceBonus);
    }

    public boolean hasBonus(int weatherInnate) {

        switch (weatherInnate) {
            case weatherNone:
                return false;

            case weatherFire:
                return bonusToHot();

            case weatherIce:
                return !bonusToHot();

            case weatherLight:
                return isDaytime();

            case weatherDark:
                return !isDaytime();

            case weatherWind:
                return bonusToWind();

            case weatherWater:
                return bonusToHumidity();

            default:
                return false;
        }

    }
}

