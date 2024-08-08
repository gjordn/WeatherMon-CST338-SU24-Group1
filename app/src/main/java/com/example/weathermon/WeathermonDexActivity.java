package com.example.weathermon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.database.entities.Monster;
import com.example.weathermon.viewholders.WeathermonDexAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeathermonDexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeathermonDexAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private WeathermonRepository repository;
    private List<Weathermon> weathermonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathermon_dex);

        repository = WeathermonRepository.getRepository(getApplication());

        recyclerView = findViewById(R.id.recycler_view_weathermon_dex);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        weathermonList = getWeathermonList();
        adapter = new WeathermonDexAdapter(weathermonList);
        recyclerView.setAdapter(adapter);
    }

    public List<Weathermon> getWeathermonList() {
        List<Weathermon> weathermonList = new ArrayList<>();
        weathermonList.add(new Weathermon("No Weather", "None", R.drawable.noweatherlogo, true));
        weathermonList.add(new Weathermon("Fire", "Fire", R.drawable.firelogo, true));
        weathermonList.add(new Weathermon("Ice", "Ice", R.drawable.icelogo, true));
        weathermonList.add(new Weathermon("Light", "Light", R.drawable.lightlogo, true));
        weathermonList.add(new Weathermon("Shadow", "Shadow", R.drawable.darklogo, true));
        weathermonList.add(new Weathermon("Wind", "Wind", R.drawable.windlogo, true));
        weathermonList.add(new Weathermon("Water", "Water", R.drawable.waterlogo, true));
        return weathermonList;
    }
}









