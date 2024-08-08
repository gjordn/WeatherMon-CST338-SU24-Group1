package com.example.weathermon;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

        // Set up the back button
        ImageButton buttonBackToMain = findViewById(R.id.buttonBackToMain);
        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and return to the previous one
            }
        });
    }

    public List<Weathermon> getWeathermonList() {
        List<Weathermon> weathermonList = new ArrayList<>();
        weathermonList.add(new Weathermon("Normal Type", "Found in normal temperature and weather conditions.", R.drawable.noweatherlogo, true));
        weathermonList.add(new Weathermon("Fire Type", "Found in sunny and extremely hot weather conditions", R.drawable.firelogo, true));
        weathermonList.add(new Weathermon("Ice Type", "Found in snowy and extremely cold weather conditions ", R.drawable.icelogo, true));
        weathermonList.add(new Weathermon("Light Type", "Found during the day in areas with lots of sun", R.drawable.lightlogo, true));
        weathermonList.add(new Weathermon("Shadow Type", "Found at night or dark conditions like solar eclipses", R.drawable.darklogo, true));
        weathermonList.add(new Weathermon("Wind Type", "Found in windy conditions, and natural disasters like hurricanes and tornadoes", R.drawable.windlogo, true));
        weathermonList.add(new Weathermon("Water Type", "Found in areas near water or during rainy conditions", R.drawable.waterlogo, true));
        return weathermonList;
    }
}








