package com.example.weathermon.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    private int userPermisionLevel;

    private static final int USER_PERMISION_NORMAL=1;
    private static final int USER_PERMISION_ADMIN =2;


    // Constructor, getters, and setters
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userPermisionLevel =USER_PERMISION_NORMAL;
    }

    public User(String username, String password, int userPermision) {
        this.username = username;
        this.password = password;
        this.userPermisionLevel =userPermision;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserPermisionLevel() {
        return userPermisionLevel;
    }

    public void setUserPermisionLevel(int userPermisionLevel) {
        this.userPermisionLevel = userPermisionLevel;
    }
}

