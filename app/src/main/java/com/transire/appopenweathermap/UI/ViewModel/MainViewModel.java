package com.transire.appopenweathermap.UI.ViewModel;

import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.transire.appopenweathermap.R;
import com.transire.appopenweathermap.databinding.ActivityMainBinding;
import com.transire.appopenweathermap.services.datasource.ForecastDataSource;
import com.transire.appopenweathermap.services.model.Forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Data
public class MainViewModel extends ViewModel {
    private Forecast forecastData;
    private MutableLiveData<Forecast> data_weather;
    private ForecastDataSource sForecastDataSource;
    public ActivityMainBinding binding;

    public MainViewModel() {
        sForecastDataSource = ForecastDataSource.getInstance();
        data_weather = new MutableLiveData<>();
    }


    public void getForecastData() {
        final EditText city = binding.editQuery;
        sForecastDataSource.getForecastData(city.getText().toString(), new Callback<Forecast>() {
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
