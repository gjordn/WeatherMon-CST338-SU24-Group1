package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Ability.class, parentColumns = "ability_id", childColumns = "ability1"),
        @ForeignKey(entity = Ability.class, parentColumns = "ability_id", childColumns = "ability2"),
        @ForeignKey(entity = Ability.class, parentColumns = "ability_id", childColumns = "ability3")
})
public class Monster {
    @PrimaryKey(autoGenerate = true)
    public int monster_id;

    public String monster_name;
    public int ability1;
    public int ability2;
    public int ability3;
    public int baseHP;
    public int baseAttack;
    public int baseDefense;
    public int weatherInnate;

    // Getters and setters

    public int getMonster_id() {
        return monster_id;
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

    public void setMonster_id(int monster_id) {
        this.monster_id = monster_id;

    }
}

