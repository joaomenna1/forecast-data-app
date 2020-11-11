package com.transire.appopenweathermap.services.datasource.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ForecastDataSourceConfig {

    protected static Retrofit retrofitBasic;

    private static final String BASE_URL_API_WEATHER = "http://api.openweathermap.org";

    public ForecastDataSourceConfig() {
        createService();
    }

    private void createService() {
        retrofitBasic = config();
    }

    private Retrofit config() {
        return new Retrofit
                .Builder()
                .baseUrl(BASE_URL_API_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
