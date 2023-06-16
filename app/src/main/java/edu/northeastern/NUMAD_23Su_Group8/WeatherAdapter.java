package edu.northeastern.NUMAD_23Su_Group8;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> implements Filterable{

    private List<WeatherCard> weatherCardList;
    private List<WeatherCard> filteredWCList;
    private final Context context;
    private CardClickListener listener;

    public WeatherAdapter(List<WeatherCard> weatherCardList, Context context) {
        this.weatherCardList = weatherCardList;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.locationName.setText(weatherCardList.get(position).getLocationName());
        holder.state.setText(weatherCardList.get(position).getState());
        holder.country.setText(weatherCardList.get(position).getCountry());

        holder.deleteBtn.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                removeItem(currentPosition);
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

    @Override
    public int getItemCount() {
        return weatherCardList.size();
    }


    public void updateData(List<WeatherCard> newList) {
        this.weatherCardList = newList;
        notifyDataSetChanged();
    }
    public void removeItem(int position) {
        weatherCardList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * getFilter() -- this method is implemented from the Filterable class and is used to
     * pare down the list of results from the RecyclerView and return the smaller list to the
     * user. The getFilter() method has to be overridden
     * <p>
     * TODO DOES NOT WORK CURRENTLY.
     *
     **/
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchQuery = constraint.toString().toLowerCase().trim();

                List<WeatherCard> filteredList = new ArrayList<>();
                for (WeatherCard card : weatherCardList) {
                    if (card.toString().toLowerCase().contains(searchQuery)) {
                        filteredList.add(card);
                    }
                }
                FilterResults res = new FilterResults();

                res.values = filteredList;

                return res;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                WeatherAdapter.this.filteredWCList = (List<WeatherCard>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void onCardListener(CardClickListener listener) {
        this.listener = listener;
    }
}
