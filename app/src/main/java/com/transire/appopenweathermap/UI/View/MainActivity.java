package com.transire.appopenweathermap.UI.View;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.transire.appopenweathermap.R;
import com.transire.appopenweathermap.UI.ViewModel.MainViewModel;
import com.transire.appopenweathermap.databinding.ActivityMainBinding;
import com.transire.appopenweathermap.databinding.ActivityMainBindingImpl;
import com.transire.appopenweathermap.services.model.Forecast;
import com.transire.appopenweathermap.services.model.Weather;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;;
    private static final String URL_IMG_API_WEATHER = "http://openweathermap.org/img/w/";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setComponent();
        viewModel.getForecastData();
    }

    private void setComponent() {
        configureObservers();
    }

    private void configureObservers() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getData_weather().observe(
                this,
                this:: setForecastUI
        );
    }

    private void setForecastUI(Forecast forecast) {

        TextView data = findViewById(R.id.txt_data_forecast);
        ImageView ico_forecast = findViewById(R.id.imageView);

        Double temperature = forecast.getMain().getTemp();
        Weather weather = forecast.getWeather().get(0);
        String icoPath = weather.getIcon();
        Glide
                .with(getApplicationContext())
                .load(URL_IMG_API_WEATHER + icoPath + ".png")
                .into(ico_forecast);

        data.setText(temperature.intValue() + "º");
    }
}
