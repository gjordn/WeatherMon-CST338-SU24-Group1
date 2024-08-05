package com.example.weathermon.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherStackInterface {

    String CURRENT_LOCATION_BY_IP = "fetch:ip";
    String BASE_URL = "http://api.weatherstack.com";
    String ENGLISH_UNITS="f";

    @GET("/current?access_key=16a73729c2644627267ef5f627f48309")
    Call<WeatherstackWeatherHolder> getWeatherStackWeather(@Query("query") String location,
                                                           @Query("units") String units);

}
