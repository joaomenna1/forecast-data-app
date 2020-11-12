package com.transire.appopenweathermap.UI.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.transire.appopenweathermap.R;
import com.transire.appopenweathermap.services.model.Forecast;

import java.util.List;

public class AdapterForecast extends RecyclerView.Adapter<AdapterForecast.ForecastHolder> {

    private List<Forecast> arrayForecast;
    private Context context;
    private static final String URL_IMG_API_WEATHER = "http://openweathermap.org/img/w/";


    public AdapterForecast(List<Forecast> arrayForecast, Context context) {
        this.arrayForecast = arrayForecast;
        this.context = context;
    }

    @NonNull
    @Override
    public ForecastHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast_data, parent, false);
        AdapterForecast.ForecastHolder forecastHolder = new AdapterForecast.ForecastHolder(v);
        return forecastHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastHolder holder, int position) {
        Forecast forecast = arrayForecast.get(position);
        requestImage(holder.ico_weather, forecast.getWeather().get(0).getIcon());
        holder.forecastData.setText(forecast.getMain().getTemp().intValue());
        holder.city.setText(forecast.getName());
    }

    private void requestImage(ImageView ico_weather, String imagePath) {
        Glide
                .with(context)
                .load(URL_IMG_API_WEATHER + imagePath + ".png")
                .into(ico_weather);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ForecastHolder extends RecyclerView.ViewHolder {
        public TextView forecastData;
        public TextView city;
        public ImageView ico_weather;

        public ForecastHolder( View itemView) {
            super(itemView);
            forecastData = itemView.findViewById(R.id.txt_data_forecast);
            city = itemView.findViewById(R.id.txt_city);
            ico_weather = itemView.findViewById(R.id.imageView);
        }
    }
}
