package edu.northeastern.NUMAD_23Su_Group8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;

public class WebServiceActivity extends AppCompatActivity {

  private ActivityWebServiceBinding binding;
  RecyclerView weatherRecyclerView;
  List<WeatherCard> weatherCardList;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*
     * using binding instead of 'setContentView(R.layout.[[activity name]])', as it makes accessing
     * the elements of the UI a bit nicer. Check ch 11 of our books to read more on it.
     */
    binding = binding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    // setting toolbar with back button that navigates to the main page.
    Toolbar toolbar = binding.webServiceToolbar;
    toolbar.setTitle("Web Service");
    toolbar.setTitleTextColor(Color.WHITE);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    binding.weatherSearchView.onActionViewExpanded();
    binding.weatherSearchView.clearFocus();

    // instantiating the weatherCardList for the recyclerView
    weatherCardList = new ArrayList<>();

    weatherRecyclerView = binding.weatherRecyclerView;

    // setting this as fixed for now, but if we need to adapt this later we can.
    weatherRecyclerView.setHasFixedSize(true);

    weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    // TODO add swipe-to-delete functionality here using ItemTouchHelper.

    weatherRecyclerView.setAdapter(new WeatherAdapter(weatherCardList, this));
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
}