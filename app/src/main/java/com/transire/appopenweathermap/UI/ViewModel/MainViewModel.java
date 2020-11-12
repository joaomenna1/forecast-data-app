package com.transire.appopenweathermap.UI.ViewModel;

import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.transire.appopenweathermap.databinding.ActivityMainBinding;
import com.transire.appopenweathermap.services.datasource.ForecastDataSource;
import com.transire.appopenweathermap.services.model.Forecast;

import lombok.Data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    public Forecast forecastData;
    public MutableLiveData<Forecast> data_weather;
    private ForecastDataSource sForecastDataSource;
    public ActivityMainBinding binding;

    public MainViewModel() {
        sForecastDataSource = ForecastDataSource.getInstance();
        data_weather = new MutableLiveData<>();
    }


    public void getForecastData(String city) {
        sForecastDataSource.getForecastData(city, new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                if (response.isSuccessful()) {
                    forecastData = response.body();
                    data_weather.postValue(forecastData);

                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("Error", "Failed to get forecasData" + t.getMessage());
            }
        });
    }
}
