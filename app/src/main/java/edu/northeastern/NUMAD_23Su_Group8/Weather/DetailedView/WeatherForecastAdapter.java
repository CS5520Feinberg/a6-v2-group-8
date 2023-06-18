package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.content.res.ResourcesCompat;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherIconHelper;
import edu.northeastern.NUMAD_23Su_Group8.R;
import java.util.List;

public class WeatherForecastAdapter extends ArrayAdapter<WeatherForecastCard> {

  private final List<WeatherForecastCard> forecastList;
  private final Context context;

  public WeatherForecastAdapter(@NonNull Context context,
      @NonNull List<WeatherForecastCard> forecastList) {
    super(context, 0, forecastList);
    this.forecastList = forecastList;
    this.context = context;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View itemView, @NonNull ViewGroup parent) {
    WeatherForecastCard card = getItem(position);
    if (itemView == null) {
      itemView = LayoutInflater.from(getContext())
          .inflate(R.layout.weather_forecast_card, parent, false);
    }
    TextView temperatureTextView = itemView.findViewById(R.id.temp2);
    TextView dayDateTextView = itemView.findViewById(R.id.daydate2);
    TextView weatherDescriptionTextView = itemView.findViewById(R.id.weather_description2);
    ImageView weatherImageView = itemView.findViewById(R.id.weatherIcon);

    temperatureTextView.setText(card.getTemperature());
    dayDateTextView.setText(card.getCurrentDate());
    weatherDescriptionTextView.setText(card.getWeatherDescription());

    int iconResource = OpenWeatherIconHelper.getWeatherIconImageResource(context, card.getWeatherIcon());
    Drawable iconDrawable = ResourcesCompat.getDrawable(context.getResources(), iconResource, context.getTheme());
    weatherImageView.setImageDrawable(iconDrawable);

    return itemView;
  }
}
