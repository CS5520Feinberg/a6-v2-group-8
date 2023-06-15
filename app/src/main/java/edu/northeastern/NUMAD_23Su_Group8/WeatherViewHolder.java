package edu.northeastern.NUMAD_23Su_Group8;

import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * WeatherViewHolder -- basic implementation of what will make up an individual item in the RecyclerView list
 * -- includes location name, the weather, and a delete button.
 */
public class WeatherViewHolder extends RecyclerView.ViewHolder {

    public TextView locationName;
    public TextView state;
    public TextView country;
    public Button deleteBtn;

    public SearchView searchView;
    public WeatherViewHolder(@NonNull View itemView) {
        super(itemView);
        this.locationName = itemView.findViewById(R.id.locationName);
        this.state = itemView.findViewById(R.id.state);
        this.country = itemView.findViewById(R.id.country);
        this.deleteBtn = itemView.findViewById(R.id.deleteBtn);
        this.searchView = itemView.findViewById(R.id.weatherSearchView);
    }
}
