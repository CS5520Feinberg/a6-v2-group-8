package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import edu.northeastern.NUMAD_23Su_Group8.R;
import java.util.List;

public class WeatherForecastAdapter extends ArrayAdapter<WeatherForecastCard> {

    private final List<WeatherForecastCard> forecastList;
    private final Context context;

    public WeatherForecastAdapter(@NonNull Context context, @NonNull List<WeatherForecastCard> forecastList) {
        super(context, 0, forecastList);
        this.forecastList = forecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View itemView, @NonNull ViewGroup parent) {
        WeatherForecastCard card = getItem(position);
        if(itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.weather_forecast_card, parent, false);
        }
        TextView temp = itemView.findViewById(R.id.temp2);
        temp.setText(card.getTemp());
        ImageView weather = itemView.findViewById(R.id.weather_icon2);
        String name = "@drawable/_"+card.getWeatherIcon();
        int res = context.getResources().getIdentifier(name, "null", context.getPackageName());
//        Drawable drawable = ContextCompat.getDrawable(context, res);
//        weather.setImageDrawable(drawable);
        TextView daydate = itemView.findViewById(R.id.daydate2);
        daydate.setText(card.getCurrentDate());
        TextView weatherDesc =itemView.findViewById(R.id.weather_description2);
        weatherDesc.setText(card.getWeatherDescription());
        return itemView;
    }

    //    public WeatherForecastAdapter(List<WeatherForecastCard> forecastList, Context context) {
//        super(context, 0);
//        this.forecastList = forecastList;
//        this.context = context;
//    }


//    @NonNull
//    @Override
//    public WeatherForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new WeatherForecastViewHolder(LayoutInflater.from(context)
//                .inflate(R.layout.weather_forecast_card, null));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position) {
//        holder.temp.setText(forecastList.get(position).getTemp());
//        holder.weather.setText(forecastList.get(position).getWeather());
//        holder.date.setText(forecastList.get(position).getCurrentDate());
//        holder.weatherDesc.setText(forecastList.get(position).getWeatherDescription());
//    }
//
//    @Override
//    public int getItemCount() {
//        return forecastList.size();
//    }
}
