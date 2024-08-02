package com.example.weathermon;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeathermonDexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeathermonDexAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathermon_dex);

        ArrayList<Weathermon> weathermonList = new ArrayList<>();
        weathermonList.add(new Weathermon("Bulbasaur", "Poison, Grass", R.drawable.bulbasaur, true));
        weathermonList.add(new Weathermon("Ivysaur", "Poison, Grass", R.drawable.ivysaur, false));
        // Add more Weathermon as needed

        recyclerView = findViewById(R.id.recycler_view_weathermon_dex);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new WeathermonDexAdapter(weathermonList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}

