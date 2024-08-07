package com.example.weathermon;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.database.WeathermonRepository;
import com.example.weathermon.viewholders.WeathermonDexAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeathermonDexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeathermonDexAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private WeathermonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathermon_dex);

        repository = WeathermonRepository.getRepository(getApplication());

        recyclerView = findViewById(R.id.recycler_view_weathermon_dex);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Weathermon> weathermonList = getWeathermonList();
        adapter = new WeathermonDexAdapter(weathermonList);
        recyclerView.setAdapter(adapter);
    }

    private List<Weathermon> getWeathermonList() {
        List<Weathermon> weathermonList = new ArrayList<>();
        weathermonList.add(new Weathermon("Normal Type", "Found in normal weather", R.drawable.noweatherlogo, true));
        weathermonList.add(new Weathermon("Fire Type", "Found in hot weather conditions", R.drawable.firelogo, true));
        weathermonList.add(new Weathermon("Ice Type", "Found in extremely cold weather", R.drawable.icelogo, true));
        weathermonList.add(new Weathermon("Light Type", "Found in sunny conditions", R.drawable.lightlogo, true));
        weathermonList.add(new Weathermon("Shadow Type", "Found in night and dark conditions", R.drawable.darklogo, true));
        weathermonList.add(new Weathermon("Wind Type", "Found in windy conditions", R.drawable.windlogo, true));
        weathermonList.add(new Weathermon("Water Type", "Found in rainy and wet conditions", R.drawable.waterlogo, true));
        return weathermonList;
    }
}








