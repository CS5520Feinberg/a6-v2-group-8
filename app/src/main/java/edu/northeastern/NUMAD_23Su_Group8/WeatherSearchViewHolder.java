package edu.northeastern.NUMAD_23Su_Group8;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherSearchViewHolder extends RecyclerView.ViewHolder {

  public TextView cityName;

  public WeatherSearchViewHolder(@NonNull View itemView) {
    super(itemView);
    this.cityName = itemView.findViewById(R.id.txt_city_name);
  }
}
