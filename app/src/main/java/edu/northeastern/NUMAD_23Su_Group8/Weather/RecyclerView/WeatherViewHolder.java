package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.R;

/**
 * WeatherViewHolder -- basic implementation of what will make up an individual item in the
 * RecyclerView list -- includes location name, the weather, and a delete button.
 */
public class WeatherViewHolder extends RecyclerView.ViewHolder {

  public TextView locationName;
  public TextView temperature;
  public ImageView icon;
  public Button deleteBtn;
  public Button moreDetails;

  public WeatherViewHolder(@NonNull View itemView) {
    super(itemView);
    this.locationName = itemView.findViewById(R.id.locationName);
    this.temperature = itemView.findViewById(R.id.temperature);
    this.icon = itemView.findViewById(R.id.card_weather_icon);
    this.deleteBtn = itemView.findViewById(R.id.deleteBtn);
    this.moreDetails = itemView.findViewById(R.id.moreDetailsBtn);
  }
}
