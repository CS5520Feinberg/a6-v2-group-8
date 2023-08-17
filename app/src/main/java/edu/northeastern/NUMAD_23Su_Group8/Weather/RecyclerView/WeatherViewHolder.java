package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.R;

/**
 * WeatherViewHolder -- basic implementation of what will make up an individual item in the
 * RecyclerView list -- includes location name, the weather, and a delete button.
 */
public class WeatherViewHolder extends RecyclerView.ViewHolder {

  private final TextView locationName;
  private final TextView temperature;
  private final ImageView icon;
  private final Button deleteBtn;
  private final Button moreDetailsBtn;

  public WeatherViewHolder(@NonNull View itemView) {
    super(itemView);
    this.locationName = itemView.findViewById(R.id.locationName);
    this.temperature = itemView.findViewById(R.id.temperature);
    this.icon = itemView.findViewById(R.id.card_weather_icon);
    this.deleteBtn = itemView.findViewById(R.id.deleteBtn);
    this.moreDetailsBtn = itemView.findViewById(R.id.moreDetailsBtn);
  }

  public TextView getLocationName() {
    return locationName;
  }

  public TextView getTemperature() {
    return temperature;
  }

  public ImageView getIcon() {
    return icon;
  }

  public Button getDeleteBtn() {
    return deleteBtn;
  }

  public Button getMoreDetailsBtn() {
    return moreDetailsBtn;
  }
}
