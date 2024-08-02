package com.example.weathermon.viewholders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.Card;
import com.example.weathermon.database.entities.CardWithMonster;

import java.util.List;

public class CardMaintenanceViewModel extends AndroidViewModel {
    private final WeathermonRepository repository;

    public CardMaintenanceViewModel(Application application){
        super(application);
        repository = WeathermonRepository.getRepository(application);
    }

    public LiveData<List<CardWithMonster>> getAllCardsByID(int userID) {
        return repository.getCardsWithMonsterByUserID(userID);
    }

    public void insert(Card card){
        repository.insertCard(card);
    }
}
