package com.example.weathermon.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weathermon.database.Admin;

import java.util.List;

@Dao
public interface AdminDao {
    @Insert
    void insert(Admin admin);

    @Update
    void update(Admin admin);

    @Delete
    void delete(Admin admin);

    @Query("SELECT * FROM admins WHERE id = :id")
    Admin getAdminById(int id);

    @Query("SELECT * FROM admins")
    List<Admin> getAllAdmins();
}
