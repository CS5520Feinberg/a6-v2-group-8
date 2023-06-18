package edu.northeastern.NUMAD_23Su_Group8;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherViewHolder>{

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
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_view, parent, false);
    return new WeatherViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.locationName.setText(weatherCardList.get(position).getLocationName());
    holder.temperature.setText(weatherCardList.get(position).getTemperature());

    holder.deleteBtn.setOnClickListener(v -> {
      int currentPosition = holder.getAdapterPosition();
      if (currentPosition != RecyclerView.NO_POSITION) {
        this.removeItem(currentPosition);
      }
    });

    // pass the selected city name through the listener to weather details activity
    holder.moreDetails.setOnClickListener(v -> {
      int currentPosition = holder.getAdapterPosition();
      if (currentPosition != RecyclerView.NO_POSITION) {
        listener.onSeeMoreClick(weatherCardList.get(position).getLocationName());
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
