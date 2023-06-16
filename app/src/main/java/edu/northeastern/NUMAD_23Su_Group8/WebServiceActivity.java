package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;
import java.util.ArrayList;
import java.util.List;

public class WebServiceActivity extends AppCompatActivity {


  private ActivityWebServiceBinding binding;

  private Toolbar toolbar;
  private SearchView weatherSearchView;

  private RecyclerView weatherRecyclerView;
  private WeatherAdapter weatherRecyclerViewAdapter;

  private List<WeatherCard> weatherCardList;
  private List<WeatherCard> updatedWCList;

  DummyDataGenerator dummyDataGenerator;

  private void setupToolbar(ActivityWebServiceBinding binding) {
    // setting toolbar with back button that navigates to the main page.
    this.toolbar = binding.webServiceToolbar;

    this.toolbar.setTitle("Web Service");
    this.toolbar.setTitleTextColor(Color.WHITE);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void setupSearchView(ActivityWebServiceBinding binding) {
    // expanding search bar so tap takes up entire area of the element (not .
    this.weatherSearchView = binding.weatherSearchView;

    this.weatherSearchView.onActionViewExpanded();
    this.weatherSearchView.clearFocus();
  }

  private void setupRecyclerView(ActivityWebServiceBinding binding) {
    // setting this as fixed for now, but if we need to adapt this later we can.
    this.weatherRecyclerView = binding.weatherRecyclerView;

    this.weatherRecyclerView.setHasFixedSize(true);
    this.weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    this.weatherRecyclerViewAdapter = new WeatherAdapter(dummyDataList, this);

    // TODO substitute this out with calls from API.
    this.weatherCardList = dummyDataList;


    /*
     * lines 77:96 represent the search filtering functionality required for our weather app.
     * The code provides weatherSearchView with a listener that waits for the user to start typing.
     * Upon doing so, the code iterates through the list of WeatherCards that are added and
     * matches the appropriate cards to the user's search (based on locationName). Then, makes the
     * adapter's updateData call with the new list.
     */
    this.weatherSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {

        List<WeatherCard> updatedWCList = new ArrayList<>();
        for (WeatherCard card : weatherCardList) {
          if (card.getLocationName().toLowerCase().contains(newText.toLowerCase())) {
            updatedWCList.add(card);
          }
        }
        WebServiceActivity.this.weatherRecyclerViewAdapter.updateData(updatedWCList);
        return true;
      }
    });

    // set up a listener for city card click
    CardClickListener listener = new CardClickListener() {
      @Override
      public void onSeeMoreClick(String city) {
        Intent intent = new Intent(WebServiceActivity.this, WeatherForecastDetailsActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
      }
    };

    this.weatherRecyclerViewAdapter.onCardListener(listener);
    this.weatherRecyclerView.setAdapter(this.weatherRecyclerViewAdapter);
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