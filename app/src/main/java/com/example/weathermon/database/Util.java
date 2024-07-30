package com.example.weathermon.database;

import android.content.Context;
import android.widget.Toast;

public class Util {
    public static final String LOGGING_TAG = "WeathermonTAG";
    public static final String WEATHERMON_LOGGED_IN_USER_ID = "com.example.weathermon.USER_ID";

    private void toastMaker(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
