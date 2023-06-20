package edu.northeastern.NUMAD_23Su_Group8.Weather.Search;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WeatherSearchAdapter extends RecyclerView.Adapter<WeatherSearchViewHolder>  implements Filterable {

  private List<String> citiesList;
  private List<String> citiesFilteredList;
  private CityFilter cityFilter;
  private LayoutInflater inflater;

  public WeatherSearchAdapter(List<String> citiesListParam) {
    this.citiesList = citiesListParam;
    this.citiesFilteredList = citiesListParam;
    this.citiesFilteredList = new ArrayList<>(citiesListParam);
  }

  @NonNull
  @Override
  public WeatherSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherSearchViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return this.citiesList.size();
  }

  @Override
  public Filter getFilter() {
    if (cityFilter == null) {
      cityFilter = new CityFilter();
    }
    return cityFilter;
  }

  private class CityFilter extends Filter {

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
      FilterResults results = new FilterResults();
      String searchQuery = constraint.toString().toLowerCase().trim();

      List<String> filteredList = new ArrayList<>();

      for (String city : citiesList) {
        if (city.toLowerCase().contains(searchQuery)) {
          filteredList.add(city);
        }
      }

      results.values = filteredList;
      results.count = filteredList.size();

      return results;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
      citiesFilteredList = (List<String>) results.values;
      notifyDataSetChanged();
    }
  }
}
