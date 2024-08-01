package com.example.weathermon.database.entities;

import java.util.Objects;

public class CardWithMonster {
    private int cardID;
    private String cardCustomName;
    private int monsterID;
    private int monsterXP;
    private int userID;
    private int monsterLevel;
    private int monster_id;
    private String monster_name;
    private int ability1;
    private int ability2;
    private int ability3;
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int weatherInnate;

    public CardWithMonster(int cardID, String cardCustomName, int monsterID, int monsterXP, int userID, int monsterLevel, int monster_id, String monster_name, int ability1, int ability2, int ability3, int baseHP, int baseAttack, int baseDefense, int weatherInnate) {
        this.cardID = cardID;
        this.cardCustomName = cardCustomName;
        this.monsterID = monsterID;
        this.monsterXP = monsterXP;
        this.userID = userID;
        this.monsterLevel = monsterLevel;
        this.monster_id = monster_id;
        this.monster_name = monster_name;
        this.ability1 = ability1;
        this.ability2 = ability2;
        this.ability3 = ability3;
        this.baseHP = baseHP;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.weatherInnate = weatherInnate;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardCustomName() {
        return cardCustomName;
    }

    public void setCardCustomName(String cardCustomName) {
        this.cardCustomName = cardCustomName;
    }

    public int getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(int monsterID) {
        this.monsterID = monsterID;
    }

    public int getMonsterXP() {
        return monsterXP;
    }

    public void setMonsterXP(int monsterXP) {
        this.monsterXP = monsterXP;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMonsterLevel() {
        return monsterLevel;
    }

    public void setMonsterLevel(int monsterLevel) {
        this.monsterLevel = monsterLevel;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardWithMonster that = (CardWithMonster) o;
        return cardID == that.cardID && monsterID == that.monsterID && monsterXP == that.monsterXP && userID == that.userID && monsterLevel == that.monsterLevel && monster_id == that.monster_id && ability1 == that.ability1 && ability2 == that.ability2 && ability3 == that.ability3 && baseHP == that.baseHP && baseAttack == that.baseAttack && baseDefense == that.baseDefense && weatherInnate == that.weatherInnate && Objects.equals(cardCustomName, that.cardCustomName) && Objects.equals(monster_name, that.monster_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardID, cardCustomName, monsterID, monsterXP, userID, monsterLevel, monster_id, monster_name, ability1, ability2, ability3, baseHP, baseAttack, baseDefense, weatherInnate);
    }


}
