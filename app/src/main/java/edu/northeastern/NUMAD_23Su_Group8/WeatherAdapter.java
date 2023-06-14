package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private final List<WeatherCard> weatherCardList;
    private final Context context;

    public WeatherAdapter(List<WeatherCard> weatherCardList, Context context) {
        this.weatherCardList = weatherCardList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_web_service, parent, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
