package com.transire.appopenweathermap.services.datasource;

import com.transire.appopenweathermap.services.datasource.Config.ForecastDataSourceConfig;
import com.transire.appopenweathermap.services.interfaces.IForecastData;
import com.transire.appopenweathermap.services.model.Forecast;

import retrofit2.Callback;
import retrofit2.Call;

public class ForecastDataSource extends ForecastDataSourceConfig {
    private static ForecastDataSource instance;
    private static final String API_KEY = "32cb311f9d3fca4c82d8deec547f8f05";

    public ForecastDataSource() {
        super();
    }

    public static ForecastDataSource getInstance() {
        if (instance == null) {
            instance = new ForecastDataSource();
        }

        return instance;
    }

    private IForecastData forecastApi = retrofitBasic.create(IForecastData.class);

    public void getForecastData(String city,Callback<Forecast> callback) {
        Call<Forecast> call = forecastApi.handleForecastData(
                city,
                API_KEY,
                "metric"
        );

        call.enqueue(callback);
    }


}
