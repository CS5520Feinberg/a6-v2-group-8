package edu.northeastern.NUMAD_23Su_Group8.Weather.Search;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

import edu.northeastern.NUMAD_23Su_Group8.R;

import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivitySearchSuggestionsBinding;

/*
 * This activity fires when a user submits a query in the WebServiceActivity SearchView element
 * that returns multiple matches from the OpenWeather API.
 *
 * The activity contains a simple ListView element, allowing the user to select which city
 * they'd like to add to the RecyclerView. Once a user selects the city, that city gets added to the
 * view and the activity finishes.
 */

public class SearchSuggestions extends AppCompatActivity {
    private ActivitySearchSuggestionsBinding binding;
    private ListView cityList;
    private List<String> matchingCities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchSuggestionsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // getting search results, and the Extra from the startActivityForResult() call in WebServiceActivity.
        this.cityList = binding.searchResults;
        this.matchingCities = getIntent().getStringArrayListExtra("matchingCities");

        // sorting the results so they appear in alphabetical order.
        Collections.sort(matchingCities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchingCities);

        cityList.setAdapter(adapter);

        /**
         *  sending WebServiceActivity the selected city that the user has chosen to add to the
         *  recycler view.
         */
        cityList.setOnItemClickListener((parent, view1, position, id) -> {
            String selectedCity = matchingCities.get(position);
            Intent resultIntent = new Intent();

            resultIntent.putExtra("selectedCity", selectedCity);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();


        });


    }
}