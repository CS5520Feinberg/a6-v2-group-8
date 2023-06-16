package edu.northeastern.NUMAD_23Su_Group8;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class WeatherForecastViewHolder extends RecyclerView.ViewHolder  {
    public TextView temp;
    public TextView weather;
    public TextView date;
    public TextView weatherDesc;
    public WeatherForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        this.temp = itemView.findViewById(R.id.temp);
        this.weather = itemView.findViewById(R.id.weather);
        this.date = itemView.findViewById(R.id.daydate);
        this.weatherDesc =itemView.findViewById(R.id.weather_description);
    }
}
