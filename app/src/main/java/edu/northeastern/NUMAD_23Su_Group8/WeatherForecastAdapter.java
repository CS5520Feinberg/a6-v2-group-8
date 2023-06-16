package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastViewHolder> {

    private final List<WeatherForecastCard> forecastList;
    private final Context context;

    public WeatherForecastAdapter(List<WeatherForecastCard> forecastList, Context context) {
        this.forecastList = forecastList;
        this.context = context;
    }


    @NonNull
    @Override
    public WeatherForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeatherForecastViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.weather_forecast_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position) {
        holder.temp.setText(forecastList.get(position).getTemp());
        holder.weather.setText(forecastList.get(position).getWeather());
        holder.date.setText(forecastList.get(position).getCurrentDate());
        holder.weatherDesc.setText(forecastList.get(position).getWeatherDescription());
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
