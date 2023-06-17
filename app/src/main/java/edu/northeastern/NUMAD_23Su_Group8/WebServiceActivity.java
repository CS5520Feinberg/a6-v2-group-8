package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherCities;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;
import java.util.ArrayList;
import java.util.List;

public class WebServiceActivity extends AppCompatActivity {


  private ActivityWebServiceBinding binding;

  private Toolbar toolbar;

  private SearchView weatherSearchView;
  private WeatherSearchAdapter weatherSearchAdapter;
  private List<String> weatherSearchCityList;

  private RecyclerView weatherRecyclerView;
  private WeatherRecyclerViewAdapter weatherRecyclerViewAdapter;
  private List<WeatherCard> weatherCardList;


  private void setupToolbar(ActivityWebServiceBinding binding) {
    // setting toolbar with back button that navigates to the main page.
    this.toolbar = binding.webServiceToolbar;

    this.toolbar.setTitle("Web Service - Weather");
    this.toolbar.setTitleTextColor(Color.WHITE);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setupSearchView(ActivityWebServiceBinding binding) {
    // expanding search bar so tap takes up entire area of the element (not .
    this.weatherSearchView = binding.weatherSearchView;

    this.weatherSearchView.setSubmitButtonEnabled(true);
    this.weatherSearchView.setQueryHint("Add a city");
    this.weatherSearchView.onActionViewExpanded();
    this.weatherSearchView.clearFocus();
  }

  /**
   * search filtering functionality required for our weather app.
   * <p>
   * The code provides weatherSearchView with a listener that waits for the user to start typing.
   * Upon doing so, the code iterates through the list of WeatherCards that are added and matches
   * the appropriate cards to the user's search (based on locationName). Then, makes the adapter's
   * updateData call with the new list.
   */
  private void setupSearchViewListener() {
    this.weatherSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        List<String> matchingCitiesList = new ArrayList<>();
        for (String city : WebServiceActivity.this.weatherSearchCityList) {
          if (city.toLowerCase().contains(query.toLowerCase())) {
            matchingCitiesList.add(city);
          }
        }

        if (matchingCitiesList.size() == 1) {
          // TODO: change to actual hit to API
          WeatherCard card = new WeatherCard(matchingCitiesList.get(0), "Country", "State");

          WebServiceActivity.this.weatherRecyclerViewAdapter.addCard(card);
        } else {
          Toast.makeText(WebServiceActivity.this,
                  matchingCitiesList.size() + " matches found! We need exactly 1!", Toast.LENGTH_LONG)
              .show();
        }

        WebServiceActivity.this.weatherSearchView.clearFocus();
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        WebServiceActivity.this.weatherSearchAdapter.getFilter().filter(newText);
        return false;
      }
    });
  }

  private void setupRecyclerView(ActivityWebServiceBinding binding) {
    // setting this as fixed for now, but if we need to adapt this later we can.
    this.weatherRecyclerView = binding.weatherRecyclerView;

    this.weatherRecyclerView.setHasFixedSize(true);
    this.weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  private void setupRecyclerViewListenerAndAdapter() {
    // set up a listener for city card click
    CardClickListener cardClickListener = new CardClickListener() {
      @Override
      public void onSeeMoreClick(String city) {
        Intent intent = new Intent(WebServiceActivity.this, WeatherForecastDetailsActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
      }
    };

    this.weatherRecyclerViewAdapter.setCardClickListener(cardClickListener);
    this.weatherRecyclerView.setAdapter(this.weatherRecyclerViewAdapter);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*
     * using binding instead of 'setContentView(R.layout.[[activity name]])', as it makes accessing
     * the elements of the UI a bit nicer. Check ch 11 of our books to read more on it.
     */
    binding = ActivityWebServiceBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    // instantiating the weatherCardList for the recyclerView
    this.weatherCardList = new ArrayList<>();

    this.setupToolbar(binding);
    this.setupSearchView(binding);
    this.setupRecyclerView(binding);

    // TODO substitute out the dummyData with real data from the API.
    List<WeatherCard> dummyDataList = DummyDataGenerator.generateData(this);

    this.weatherRecyclerViewAdapter = new WeatherRecyclerViewAdapter(dummyDataList);
    this.weatherSearchCityList = OpenWeatherCities.getCityList(this);
    this.weatherSearchAdapter = new WeatherSearchAdapter(this.weatherSearchCityList);

    // TODO substitute this out with calls from API.
    this.weatherCardList = dummyDataList;

    // adapter needed for searchViewListener
    this.setupSearchViewListener();

    this.setupRecyclerViewListenerAndAdapter();
  }

  /**
   * Overriding the onOptionsItemSelected method in order to facilitate the navigation of the btn
   * from this page back to home.
   *
   * @param backButton The menu item that was selected.
   * @return result from super.onOptionsItemSelected(backButton)
   */
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem backButton) {
    finish();
    return super.onOptionsItemSelected(backButton);
  }

  /**
   * This method is what saves the state of RecyclerView on screen rotation.
   *
   * @param state Bundle in which to place your saved state.
   */
  @Override
  protected void onSaveInstanceState(@NonNull Bundle state) {
    super.onSaveInstanceState(state);

    if (this.weatherRecyclerView.getLayoutManager() != null) {
      Parcelable recyclerState = this.weatherRecyclerView.getLayoutManager().onSaveInstanceState();
      state.putParcelable("recyclerState", recyclerState);
    }
  }

  /**
   * This method is what restores the saved state of recycler view on screen rotation.
   *
   * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
   */
  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (this.weatherRecyclerView.getLayoutManager() != null) {
      Parcelable recyclerState = savedInstanceState.getParcelable("recyclerState");
      this.weatherRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerState);
    }

  }
}