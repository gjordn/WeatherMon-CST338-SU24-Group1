package com.example.weathermon.database.entities;

import java.util.Objects;
import java.util.Random;


public class CardWithMonster {
    private int cardID;
    private String cardCustomName;
    private int monsterID;
    private int monsterXP;
    private int userID;
    private int monster_id;
    private String monster_name;
    private int baseHP;
    private int baseAttack;
    private int baseDefense;
    private int weatherInnate;

    public static Location battleLocation;
    public static final Double MIN_OPPONENT_XP_PERCENT = 0.7;
    public static final Double MAX_OPPONENT_XP_PERCENT = 1.5;
    public static final int POINTS_PER_DICE = 20;
    public static final double KNOCKED_OUT_HP = 0.0;


    public CardWithMonster(int cardID, String cardCustomName, int monsterID, int monsterXP, int userID, int monster_id, String monster_name, int baseHP, int baseAttack, int baseDefense, int weatherInnate) {
        this.cardID = cardID;
        this.cardCustomName = cardCustomName;
        this.monsterID = monsterID;
        this.monsterXP = monsterXP;
        this.userID = userID;
        this.monster_id = monster_id;
        this.monster_name = monster_name;
        this.baseHP = baseHP;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.weatherInnate = weatherInnate;
    }

    public CardWithMonster() {

    }

    public static CardWithMonster getTrainingOpponent(int heroXP, Monster baseOpponent) {
        CardWithMonster nearLevelOpponent = new CardWithMonster();

        nearLevelOpponent.monsterID = baseOpponent.getMonster_id();

        Random random = new Random();
        double opponentXP;
        opponentXP = heroXP * (MIN_OPPONENT_XP_PERCENT + (MAX_OPPONENT_XP_PERCENT - MIN_OPPONENT_XP_PERCENT)*random.nextDouble());
        nearLevelOpponent.monsterXP = (int) opponentXP;
        nearLevelOpponent.setCardCustomName(""); //Can't be null.

        nearLevelOpponent.monster_id = baseOpponent.getMonster_id();
        nearLevelOpponent.monster_name = baseOpponent.getMonster_name();
        nearLevelOpponent.baseHP = baseOpponent.getBaseHP();
        nearLevelOpponent.baseAttack = baseOpponent.getBaseAttack();
        nearLevelOpponent.baseDefense = baseOpponent.getBaseDefense();
        nearLevelOpponent.weatherInnate = baseOpponent.getWeatherInnate();


        return nearLevelOpponent;
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

    public int getXPToNextLevel(){
        return Card.getXPToNextLevel(this.getMonsterXP());
    }

    public int getLevelFromXP(){
        return Card.getCurrentLevel(this.monsterXP);
    }

    public static Location getBattleLocation() {
        return battleLocation;
    }

    public static void setBattleLocation(Location battleLocation) {
        CardWithMonster.battleLocation = battleLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardWithMonster that = (CardWithMonster) o;
        return cardID == that.cardID && monsterID == that.monsterID && monsterXP == that.monsterXP && userID == that.userID && monster_id == that.monster_id && baseHP == that.baseHP && baseAttack == that.baseAttack && baseDefense == that.baseDefense && weatherInnate == that.weatherInnate && Objects.equals(cardCustomName, that.cardCustomName) && Objects.equals(monster_name, that.monster_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardID, cardCustomName, monsterID, monsterXP, userID, monster_id, monster_name, baseHP, baseAttack, baseDefense, weatherInnate);
    }

    public int getTotalDefense(){
        return adjustedForWeather(adjustedForLevel(baseDefense));

    }

    private int adjustedForWeather(int currentStat) {
        if (getBattleLocation()!=null && getBattleLocation().hasBonus(getWeatherInnate())) {
            double adjustedStat = (currentStat * Monster.innateWeatherBonus);
            return (int) adjustedStat;
        }
        return currentStat;
    }

    public int getTotalAttack(){
        return adjustedForWeather(adjustedForLevel(baseAttack));
    }
    public int getTotalHP(){
        return adjustedForLevel(baseHP);
    }
    private int adjustedForLevel(int baseStat){
        double adjustedStat =(baseStat * (Math.pow(Monster.levelModifier,getLevelFromXP()-1)));
        return (int) adjustedStat;
    }


    public Boolean fight(CardWithMonster cardToBattle) {

        int heroHP = this.getTotalHP();
        int  villainHP = cardToBattle.getTotalHP();

        //Hero boosted by 1 dice because winning just 1/2 the time feels bad.  Make the user feel good.
        //Then ask for more money to continue that feeling.  Micro TRANSACTION!!!Get more dice for just
        //3.95 per month, less than a cup of coffee.  Subscription fees = continuing revenue.
        while (heroHP> KNOCKED_OUT_HP && villainHP > KNOCKED_OUT_HP){
            heroHP-= rollAttack(this.getTotalAttack()+POINTS_PER_DICE, cardToBattle.getTotalDefense());
            villainHP-=rollAttack(cardToBattle.getTotalAttack(), this.getTotalDefense()+POINTS_PER_DICE);

        }

        return (heroHP>villainHP);
    }

    private int rollAttack(int atk, int def){
        Random random = new Random();

        int atkDice=atk/POINTS_PER_DICE;
        int defDice=def/POINTS_PER_DICE;
        int atkDamage=0;
        int defBlock=0;

        for (int i=0;i<atkDice;i++){
            atkDamage+= random.nextInt(6)+1;
        }
        for (int i=0;i<defDice;i++){
            defBlock+= random.nextInt(6)+1;
        }

        int damageDone =atkDamage-defBlock;
        return Math.max(damageDone, 0);
    }
}
