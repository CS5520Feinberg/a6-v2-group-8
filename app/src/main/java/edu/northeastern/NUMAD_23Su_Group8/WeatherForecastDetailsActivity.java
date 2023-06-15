package edu.northeastern.NUMAD_23Su_Group8;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecastDetailsActivity extends AppCompatActivity {
    RecyclerView forecastListRecyclerView;
    private static final String FORECAST_LIST_KEY = "forecast_list";
    List<WeatherForecastCard> forecastList = new ArrayList<>();

    private WeatherForecastAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        forecastListRecyclerView = findViewById(R.id.forecastList);

        adapter = new WeatherForecastAdapter(forecastList, this);
        forecastListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastListRecyclerView.setAdapter(adapter);

        if (savedInstanceState != null) {
            ArrayList<WeatherForecastCard> savedDataList = savedInstanceState.getParcelableArrayList(FORECAST_LIST_KEY);
            if (savedDataList != null) {
                forecastList.addAll(savedDataList);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(FORECAST_LIST_KEY, new ArrayList<>(forecastList));
    }
}
