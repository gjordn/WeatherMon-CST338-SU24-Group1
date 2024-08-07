package com.example.weathermon;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.weathermon.database.entities.Monster;

public class MonsterTest {

    @Test
    public void testWeatherTypeToDrawableConversion() {
        assertEquals(R.drawable.noweatherlogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherNone).intValue());
        assertEquals(R.drawable.firelogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherFire).intValue());
        assertEquals(R.drawable.icelogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherIce).intValue());
        assertEquals(R.drawable.lightlogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherLight).intValue());
        assertEquals(R.drawable.darklogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherDark).intValue());
        assertEquals(R.drawable.windlogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherWind).intValue());
        assertEquals(R.drawable.waterlogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherWater).intValue());
    }

    @Test
    public void testWeatherTypeToStringConversion() {
        assertEquals("None", Monster.convertWeatherTypeToString.get(Monster.weatherNone));
        assertEquals("Fire", Monster.convertWeatherTypeToString.get(Monster.weatherFire));
        assertEquals("Ice", Monster.convertWeatherTypeToString.get(Monster.weatherIce));
        assertEquals("Light", Monster.convertWeatherTypeToString.get(Monster.weatherLight));
        assertEquals("Shadow", Monster.convertWeatherTypeToString.get(Monster.weatherDark));
        assertEquals("Wind", Monster.convertWeatherTypeToString.get(Monster.weatherWind));
        assertEquals("Water", Monster.convertWeatherTypeToString.get(Monster.weatherWater));
    }
}

