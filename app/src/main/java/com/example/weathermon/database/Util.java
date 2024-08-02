package com.example.weathermon.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class Util {
    public static final String LOGGING_TAG = "WeathermonTAG";
    public static final String WEATHERMON_SHARED_PREF_KEY = "WeatherMonPrefs";
    public static final String WEATHERMON_SHARED_PREF_USERID = "userId";
    public static final String WEATHERMON_LOGGED_IN_USER_ID = "com.example.weathermon.USER_ID";
    public static final int USER_LOGGED_OUT = -1;

    private void toastMaker(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
