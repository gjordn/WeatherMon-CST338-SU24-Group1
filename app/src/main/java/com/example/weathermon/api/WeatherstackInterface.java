package com.example.weathermon.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherstackInterface {

    public static final String CURRENT_LOCATION_BY_IP = "fetch:ip";
    public static final String BASE_URL = "http://api.weatherstack.com";
    public static final String ENGLISH_UNITS="f";

    @GET("/current?access_key=16a73729c2644627267ef5f627f48309")
    Call<WeatherstackWeatherHolder> getWeartherstackWeather(@Query("query") String location,
                                                            @Query("units") String units);

}
