package com.example.weathermon.viewholders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.Card;

import java.util.List;

public class CardMaintenanceViewModel extends AndroidViewModel {
    private final WeathermonRepository repository;

    private final LiveData<List<Card>> allCardsByID;

    public CardMaintenanceViewModel(Application application, int UserID){
        super(application);
        repository = WeathermonRepository.getRepository(application);
        allCardsByID = repository.getCardsByUserID(UserID);
    }

    public LiveData<List<Card>> getAllCardsByID() {
        return allCardsByID;
    }

    public void insert(Card card){
        repository.insertCard(card);
    }
}
