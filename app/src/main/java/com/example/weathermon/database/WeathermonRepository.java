package com.example.weathermon.database;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;

import com.example.weathermon.database.dao.AbilityDAO;
import com.example.weathermon.database.dao.CardDAO;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.dao.UserDAO;
import com.example.weathermon.database.entities.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WeathermonRepository {
    private final AbilityDAO abilityDAO;
    private final UserDAO userDAO;
    private final LocationDAO locationDAO;
    private final CardDAO cardDAO;


    private static WeathermonRepository repository;

    private WeathermonRepository(Application application){
        WeathermonDatabase db = WeathermonDatabase.getDatabase(application);
        this.abilityDAO = db.abilityDAO();
        this.userDAO = db.userDAO();
        this.locationDAO = db.locationDao();
        this.cardDAO = db.cardDAO();
    }

    public static WeathermonRepository getRepository(Application application){
        if (repository != null) {
            return repository;
        }


        Future<WeathermonRepository> future = WeathermonDatabase.databaseWriterExecutor.submit(
                new Callable<WeathermonRepository>() {
                    @Override
                    public WeathermonRepository call() throws Exception {
                        return new WeathermonRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
        }
        return null;
    }


    public void insertUser (User... user){
        WeathermonDatabase.databaseWriterExecutor.execute(()->
        {
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUserID(int userID) {
        return userDAO.getUserById(userID);
    }

}