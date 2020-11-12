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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.transire.appopenweathermap.R;
import com.transire.appopenweathermap.UI.Adapter.AdapterForecast;
import com.transire.appopenweathermap.UI.ViewModel.MainViewModel;
import com.transire.appopenweathermap.databinding.ActivityMainBinding;
import com.transire.appopenweathermap.databinding.ActivityMainBindingImpl;
import com.transire.appopenweathermap.services.model.Forecast;
import com.transire.appopenweathermap.services.model.Weather;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;;
    private ActivityMainBinding binding;
    private RecyclerView mRecyclerForecastData;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterForecast mAdapterForecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText city = binding.editQuery;

        setComponent();
        viewModel.getForecastData(city.getText().toString());
    }

    private void setComponent() {
        configureObservers();
    }

    private void configureObservers() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.data_weather.observe(
                this,
                this:: setForecastUI
        );
    }

    private void setForecastUI(List<Forecast> forecast) {
        mRecyclerForecastData = binding.recyclerForecast;
        mRecyclerForecastData.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        mRecyclerForecastData.setLayoutManager(layoutManager);
        mAdapterForecast = new AdapterForecast(getApplicationContext(), viewModel.)



    }
}
