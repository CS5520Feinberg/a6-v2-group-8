package edu.northeastern.NUMAD_23Su_Group8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;

public class WebServiceActivity extends AppCompatActivity {


  private ActivityWebServiceBinding binding;
  RecyclerView weatherRecyclerView;
  List<WeatherCard> weatherCardList;
  List<WeatherCard> updatedWCList;

  DummyDataGenerator dummyDataGenerator;

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

    // setting toolbar with back button that navigates to the main page.
    Toolbar toolbar = binding.webServiceToolbar;
    toolbar.setTitle("Web Service");
    toolbar.setTitleTextColor(Color.WHITE);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // expanding search bar so tap takes up entire area of the element (not .
    binding.weatherSearchView.onActionViewExpanded();
    binding.weatherSearchView.clearFocus();

    // instantiating the weatherCardList for the recyclerView
    weatherCardList = new ArrayList<>();

    weatherRecyclerView = binding.weatherRecyclerView;

    // setting this as fixed for now, but if we need to adapt this later we can.
    weatherRecyclerView.setHasFixedSize(true);

    weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    // TODO substitute out the dummyData with real data from the API.
    List<WeatherCard> dummyDataList = dummyDataGenerator.generateData(this);

    WeatherAdapter weatherAdapter = new WeatherAdapter(dummyDataList, this);

    // TODO subsitute this out with calls from API.
    weatherCardList = dummyDataList;


    /*
     * lines 77:96 represent the search filtering functionality required for our weather app.
     * The code provides weatherSearchView with a listener that waits for the user to start typing.
     * Upon doing so, the code iterates through the list of WeatherCards that are added and
     * matches the appropriate cards to the user's search (based on locationName). Then, makes the
     * adapter's updateData call with the new list.
     */
    binding.weatherSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

        updatedWCList = new ArrayList<>();
        for (WeatherCard card : weatherCardList) {
          if (card.getLocationName().toLowerCase().contains(newText.toLowerCase())) {
            updatedWCList.add(card);
          }
        }
        weatherAdapter.updateData(updatedWCList);
        return true;
      }
    });

    // set up a listener for city card click
    CardClickListener listener = new CardClickListener() {
      @Override
      public void onSeeMoreClick(String city) {
        Intent intent = new Intent(WebServiceActivity.this, WeatherForecastDetailsActivity.class);
        intent.putExtra("city",city);
        startActivity(intent);
      }
    };

    weatherAdapter.onCardListener(listener);
    weatherRecyclerView.setAdapter(weatherAdapter);
  }

  /**
   * Overriding the onOptionsItemSelected method in order to facilitate the navigation of the btn
   * from this page back to home.
   * @param backButton The menu item that was selected.\
   *
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem backButton) {

    finish();
    return super.onOptionsItemSelected(backButton);
  }

  /**
   * This method is what saves the state of REcyclerVIew on screen rotation.
   * @param state Bundle in which to place your saved state.
   *
   */
  @Override
  protected void onSaveInstanceState(Bundle state) {
    super.onSaveInstanceState(state);

    Parcelable recyclerState = weatherRecyclerView.getLayoutManager().onSaveInstanceState();
    state.putParcelable("recyclerState", recyclerState);
  }

  /**
   * This method is what restores the saved state of recycler view on screen rotation.
   * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
   *
   */
  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    Parcelable recyclerState = savedInstanceState.getParcelable("recyclerState");
    weatherRecyclerView.getLayoutManager().onRestoreInstanceState(recyclerState);

  }
}