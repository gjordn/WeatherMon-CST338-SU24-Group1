// Monster.java
package com.example.myapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Monster {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int hp;
    public int maxHp;
    public int xp;

    // Constructor, getters, and setters can be added here if needed
}

