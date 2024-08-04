package com.example.weathermon.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.weathermon.database.dao.AbilityDAO;
import com.example.weathermon.database.dao.CardDAO;
import com.example.weathermon.database.dao.LocationDAO;
import com.example.weathermon.database.dao.UserDAO;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;
import com.example.weathermon.database.entities.User;

import java.util.List;
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
        try {
            repository = future.get();
            return repository;
        } catch (InterruptedException | ExecutionException e) {
            Log.e("WeathermonRepository", "Error initializing repository", e);
        }
        return null;
    }

    public void insertUser (User... user){
        WeathermonDatabase.databaseWriterExecutor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }

    public void updateUserPassword(String username, String newPassword) {
        WeathermonDatabase.databaseWriterExecutor.execute(() -> {
            userDAO.updatePassword(username, newPassword);
        });
    }

    public void makeAdmin(String username) {
        WeathermonDatabase.databaseWriterExecutor.execute(() -> {
            User user = userDAO.getUserByUsernameSync(username);
            if (user != null && !user.isAdmin()) {
                userDAO.makeAdmin(username);
            }
        });
    }

    public LiveData<User> getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public LiveData<User> getUserByUserID(int userID) {
        return userDAO.getUserById(userID);
    }

    public LiveData<List<Card>> getCardsByUserID(int userID){
        return cardDAO.getCardsByUserID(userID);
    }

    public LiveData<List<CardWithMonster>> getCardsWithMonsterByUserID(int userID) {
        return cardDAO.getCardsWithMonsterByUserID(userID);
    }

    public void deleteCardByID(int cardID){
        WeathermonDatabase.databaseWriterExecutor.execute(()->
                cardDAO.deleteCardByID(cardID));
    }

    public void insertCard(Card card) {
        cardDAO.insert(card);
    }

    public void deleteUser(User user) {
        WeathermonDatabase.databaseWriterExecutor.execute(() -> userDAO.delete(user));
    }

    public void updateUser(User user) {
        WeathermonDatabase.databaseWriterExecutor.execute(() -> userDAO.update(user));
    }

      public void updateCards(Card... cards) {
        WeathermonDatabase.databaseWriterExecutor.execute(()->
                cardDAO.updateCards(cards));;
    }

    public void deleteUserByUsername(String username) {
        WeathermonDatabase.databaseWriterExecutor.execute(() -> {
            User user = userDAO.getUserByUsernameSync(username);
            if (user != null) {
                userDAO.delete(user);
            }
        });
    }
}