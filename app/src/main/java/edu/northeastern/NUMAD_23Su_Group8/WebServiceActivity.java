package edu.northeastern.NUMAD_23Su_Group8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import edu.northeastern.NUMAD_23Su_Group8.R;

import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;

public class WebServiceActivity extends AppCompatActivity {

  private ActivityWebServiceBinding binding;

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