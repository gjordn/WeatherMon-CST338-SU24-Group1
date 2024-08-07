package com.example.weathermon;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.weathermon.viewholders.WeathermonDexAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class WeathermonDexActivityTest {

    @Test
    public void testWeathermonListPopulation() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = new Intent(context, WeathermonDexActivity.class);

        try (ActivityScenario<WeathermonDexActivity> scenario = ActivityScenario.launch(intent)) {
            scenario.onActivity(activity -> {
                List<Weathermon> weathermonList = activity.getWeathermonList();

                assertNotNull("Weathermon list is null", weathermonList);
                assertEquals("Weathermon list size does not match", 7, weathermonList.size());

                // Verify the contents of the list
                assertEquals("No Weather", weathermonList.get(0).getName());
                assertEquals("Fire", weathermonList.get(1).getName());
                assertEquals("Ice", weathermonList.get(2).getName());
                assertEquals("Light", weathermonList.get(3).getName());
                assertEquals("Shadow", weathermonList.get(4).getName());
                assertEquals("Wind", weathermonList.get(5).getName());
                assertEquals("Water", weathermonList.get(6).getName());
            });
        }
    }
}

