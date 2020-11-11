package com.transire.appopenweathermap.services.interfaces;

import com.transire.appopenweathermap.services.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IForecastData {
    @GET("/data/2.5/weather")
    Call<Forecast> handleForecastData(
            @Query("q") String city,
            @Query("appid") String key,
            @Query("units") String units
    );
}
