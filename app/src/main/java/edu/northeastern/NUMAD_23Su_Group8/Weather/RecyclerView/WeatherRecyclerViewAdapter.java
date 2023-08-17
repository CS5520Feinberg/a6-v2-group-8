package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherIconHelper;
import edu.northeastern.NUMAD_23Su_Group8.R;
import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherViewHolder>{

  private Context parentContext;
  private List<WeatherCard> weatherCardList;

  private CardClickListener listener;

  public WeatherRecyclerViewAdapter(List<WeatherCard> weatherCardList) {
    this.weatherCardList = weatherCardList;
  }

  @Override
  public int getItemCount() {
    return this.weatherCardList.size();
  }

  @NonNull
  @Override
  public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    this.parentContext = parent.getContext();
    View view = LayoutInflater.from(this.parentContext)
        .inflate(R.layout.card_view, parent, false);
    return new WeatherViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.getLocationName().setText(weatherCardList.get(position).getLocationName());
    holder.getTemperature().setText(weatherCardList.get(position).getTemperature());

    int iconResource = OpenWeatherIconHelper.getWeatherIconImageResource(this.parentContext, weatherCardList.get(position).getIcon());
    Drawable iconDrawable = ResourcesCompat.getDrawable(parentContext.getResources(), iconResource, parentContext.getTheme());

    holder.getIcon().setImageDrawable(iconDrawable);

    holder.getDeleteBtn().setOnClickListener(v -> {
      int currentPosition = holder.getBindingAdapterPosition();
      if (currentPosition != RecyclerView.NO_POSITION) {
        this.removeItem(currentPosition);
      }
    });

    // pass the selected city name through the listener to weather details activity
    holder.getMoreDetailsBtn().setOnClickListener(v -> {
      int currentPosition = holder.getBindingAdapterPosition();
      if (currentPosition != RecyclerView.NO_POSITION) {
        listener.onSeeMoreClick(weatherCardList.get(position).getLatitude(), weatherCardList.get(position).getLongitude());
      }
    });
  }

  private void removeItem(int position) {
    weatherCardList.remove(position);
    notifyItemRemoved(position);
  }

  public void addCard(WeatherCard newCard) {
    weatherCardList.add(newCard);
    notifyItemInserted(weatherCardList.size() - 1);
  }

  public void setCardClickListener(CardClickListener listener) {
    this.listener = listener;
  }
}
