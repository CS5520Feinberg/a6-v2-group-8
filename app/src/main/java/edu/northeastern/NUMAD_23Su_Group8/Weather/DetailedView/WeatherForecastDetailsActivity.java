package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import static edu.northeastern.NUMAD_23Su_Group8.BuildConfig.OPEN_WEATHER_API_KEY;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.res.ResourcesCompat;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherIconHelper;
import edu.northeastern.NUMAD_23Su_Group8.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Activity to view and manage the weather details for the city. Displays current weather and next 10 days forecast.
 */
public class WeatherForecastDetailsActivity extends AppCompatActivity {

  ListView forecastListView;
  private static final String FORECAST_LIST_KEY = "forecast_list";
  List<WeatherForecastCard> forecastList = new ArrayList<>();
  String baseURL = "https://api.openweathermap.org/data/2.5/";

  /**
   * For getting the forecast data for the next  30 days.
   */
  String proURL = "https://pro.openweathermap.org/data/2.5/forecast/climate";
  private final Handler handler = new Handler();
  private ProgressBar progressBar;

  private WeatherForecastAdapter adapter;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_details);
    String lat = getIntent().getStringExtra("lat");
    String lon = getIntent().getStringExtra("lon");
    getData(lat,lon);
    progressBar = findViewById(R.id.progressBar);

    if (savedInstanceState != null) {
      ArrayList<WeatherForecastCard> savedDataList = savedInstanceState.getParcelableArrayList(
          FORECAST_LIST_KEY);
      if (savedDataList != null) {
        forecastList.addAll(savedDataList);
      }
    }
  }

  /**
   * Gets weather data for the city.
   *
   * @param la latitude
   * @param lo longitude
   */
  private void getData(String la, String lo) {
    String lat = la;
    String lon = lo;
    try {
      String currentURL = baseURL + "weather?lat=" + lat + "&lon=" + lon + "&units=metric&appid="
          + OPEN_WEATHER_API_KEY;
      String forecastURL = proURL + "?lat=" + lat + "&lon=" + lon + "&units=metric&appid="
          + OPEN_WEATHER_API_KEY;
      HTTPConnectionThread weatherTask = new HTTPConnectionThread(new String[]{currentURL, "weatherTask"});
      HTTPConnectionThread forecastTask = new HTTPConnectionThread(new String[]{forecastURL, "forecastTask"});
      weatherTask.start();
      forecastTask.start();
    } catch (Exception e) {
      Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * To handle the forecast details list view.
   */
  public void createListView() {
    forecastListView = findViewById(R.id.forecastList);
    adapter = new WeatherForecastAdapter(this, forecastList);
    forecastListView.setAdapter(adapter);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putParcelableArrayList(FORECAST_LIST_KEY, new ArrayList<>(forecastList));
  }

  /**
   * Parse and format the response from the OpenweatherAPI to be displayed in the activity. There are 2 tasks.
   *  WeatherTask: To handle current weather data
   *  ForecastTask: To handle the forecast data.
   *  The API returns forecast for 30 days,but the data is displayed only for the next 10 days.
   *
   * @param data response data fro the APIs
   * @throws JSONException to handle JSON parse errors
   */
  private void parseData(String[] data) throws JSONException {
    switch (data[1]) {
      case "forecastTask":
        JSONObject jsonObject = new JSONObject(data[0]);
        DateFormat obj = new SimpleDateFormat("E, dd MMM");
        JSONArray jsonArray1 = jsonObject.getJSONArray("list");
        for (int i = 0; i < 10; i++) {
          JSONObject day = jsonArray1.getJSONObject(i);
          String temp = day.getJSONObject("temp").get("day") + " ℃";
          Long dat = Long.parseLong(day.get("dt").toString());
          Date cur = new Date(dat*1000);
          JSONObject weatherDetails = day.getJSONArray("weather").getJSONObject(0);
          String weather = weatherDetails.getString("main");
          String weatherDesc = weatherDetails.getString("description");
          String weatherIcon = weatherDetails.getString("icon");
          forecastList.add(
              new WeatherForecastCard(temp, obj.format(cur), weather, weatherDesc, weatherIcon));
        }
        createListView();
        progressBar.setVisibility(View.INVISIBLE);
        break;

      case "weatherTask":
        JSONObject jsonObject2 = new JSONObject(data[0]);
        DateFormat obj2 = new SimpleDateFormat("E, dd MMM");
        DateFormat time = new SimpleDateFormat("hh:mm aaa");
        Long dat = Long.parseLong(jsonObject2.get("dt").toString());
        Date cur = new Date(dat*1000);
        JSONObject weatherDetails = jsonObject2.getJSONArray("weather").getJSONObject(0);
        String weather = weatherDetails.getString("main");
        String weatherIcon = weatherDetails.getString("icon");

        TextView city = (TextView) findViewById(R.id.city_in_card);
        TextView date = (TextView) findViewById(R.id.daydate2);
        TextView weatherDesc = (TextView) findViewById(R.id.weather_description2);
        TextView temp = (TextView) findViewById(R.id.temp2);
        TextView wind = (TextView) findViewById(R.id.wind);
        ImageView icon = findViewById(R.id.weather_icon2);
        TextView sunRise = (TextView) findViewById(R.id.sunRise);
        Long sunRiseLong = Long.parseLong(jsonObject2.getJSONObject("sys").get("sunrise").toString());
        Date sunRiseTime = new Date(sunRiseLong*1000);
        TextView sunSet = (TextView) findViewById(R.id.sunSet);
        Long sunSetLong = Long.parseLong(jsonObject2.getJSONObject("sys").get("sunset").toString());
        Date sunSetTime = new Date(sunSetLong*1000);

        city.setText((CharSequence) jsonObject2.get("name"));
        date.setText((CharSequence) obj2.format(cur));
        sunRise.setText((CharSequence)"Sunrise: "+  time.format(sunRiseTime));
        sunSet.setText((CharSequence) "Sunset: "+ time.format(sunSetTime));
        weatherDesc.setText((CharSequence) weatherDetails.getString("description"));
        temp.setText(
            (CharSequence) jsonObject2.getJSONObject("main").get("temp").toString() + " ℃");
        wind.setText(
            (CharSequence) "Wind: " + jsonObject2.getJSONObject("wind").get("speed").toString()
                + " m/s");

        int iconResource = OpenWeatherIconHelper.getWeatherIconImageResource(this, weatherIcon);
        Drawable iconDrawable = ResourcesCompat.getDrawable(getResources(), iconResource,
            getTheme());
        icon.setImageDrawable(iconDrawable);
        break;
      default:
        break;
    }
  }

  /**
   * To handle the API calls for the activity in a separate thread. Each network call will be handled in
   * its own thread. Once the response is available, it is passed to parseData() function to format the data.
   */
  private class HTTPConnectionThread extends Thread {
    String[] params;

    public HTTPConnectionThread(String[] params) {
      this.params = params;
    }

    @Override
    public void run() {
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          progressBar.setVisibility(View.VISIBLE);
        }
      });
      String[] result = WeatherForecastRequestsHelper.getResponse(this.params);
      if (result != null) {
        WeatherForecastDetailsActivity.this.handler.post(
                () -> {
                  try {
                    WeatherForecastDetailsActivity.this.parseData(result);
                  } catch (JSONException e) {
                    throw new RuntimeException(e);
                  }
                });
      } else {
        WeatherForecastDetailsActivity.this.handler.post(() -> Toast.makeText(WeatherForecastDetailsActivity.this,
                        "Error occurred while retrieving data from API!", Toast.LENGTH_LONG)
                .show());
      }
    }
  }
}
