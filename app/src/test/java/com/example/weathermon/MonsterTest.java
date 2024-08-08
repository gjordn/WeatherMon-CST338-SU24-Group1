package com.example.weathermon;

import com.example.weathermon.database.entities.Monster;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

    private Weathermon monster;

    @Before
    public void setUp() {
        // Initialize a Monster object before each test
        monster = new Weathermon();
        monster.setMonster_id(1);
        monster.setName("TestMonster");
        monster.setWeatherTypeInt(Monster.weatherFire);
    }

    @Test
    public void testGetMonsterId() {
        assertEquals(1, monster.getMonster_id());
    }

    @Test
    public void testSetMonsterId() {
        monster.setMonster_id(2);
        assertEquals(2, monster.getMonster_id());
    }

    @Test
    public void testGetName() {
        assertEquals("TestMonster", monster.getName());
    }

    @Test
    public void testSetName() {
        monster.setName("NewName");
        assertEquals("NewName", monster.getName());
    }

    @Test
    public void testGetWeatherType() {
        assertEquals(Monster.weatherFire, monster.getWeatherTypeInt());
    }

    @Test
    public void testSetWeatherType() {
        monster.setWeatherTypeInt(Monster.weatherIce);
        assertEquals(Monster.weatherIce, monster.getWeatherTypeInt());
    }

    @Test
    public void testWeatherTypeToDrawable() {
        assertEquals((Integer) R.drawable.firelogo, Monster.convertWeatherTypeToDrawable.get(Monster.weatherFire));
    }

    @Test
    public void testWeatherTypeToString() {
        assertEquals("Fire", Monster.convertWeatherTypeToString.get(Monster.weatherFire));
    }
}

