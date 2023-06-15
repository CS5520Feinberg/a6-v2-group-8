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
        return new WeatherForecastViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_weather_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
