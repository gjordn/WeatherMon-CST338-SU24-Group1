package com.example.weathermon;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathermon.viewholders.WeathermonDexAdapter;

import java.util.ArrayList;

public class WeathermonDexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WeathermonDexAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weathermon_dex);

        // Initialize your Weathermon list
        ArrayList<Weathermon> weathermonList = new ArrayList<>();
        weathermonList.add(new Weathermon("Firemon", "Fire", R.drawable.firelogo, true));
        weathermonList.add(new Weathermon("Icemon", "Ice", R.drawable.icelogo, false));
        weathermonList.add(new Weathermon("Darkmon", "Dark", R.drawable.darklogo, false));
        weathermonList.add(new Weathermon("Watermon", "Water", R.drawable.waterlogo, true));
        weathermonList.add(new Weathermon("Windmon", "Wind", R.drawable.windlogo, false));
        weathermonList.add(new Weathermon("Lightmon", "Light", R.drawable.lightlogo, true));
        weathermonList.add(new Weathermon("Hotmon", "Hot Monster", R.drawable.hotmonstericon, false));
        // Add more Weathermon as needed

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recycler_view_weathermon_dex);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        adapter = new WeathermonDexAdapter(weathermonList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}



