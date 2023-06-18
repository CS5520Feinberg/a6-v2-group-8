package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import static android.content.ContentValues.TAG;

import static edu.northeastern.NUMAD_23Su_Group8.BuildConfig.OPEN_WEATHER_API_KEY;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.res.ResourcesCompat;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherIconHelper;
import edu.northeastern.NUMAD_23Su_Group8.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherForecastDetailsActivity extends AppCompatActivity {

  ListView forecastListView;
  private static final String FORECAST_LIST_KEY = "forecast_list";
  List<WeatherForecastCard> forecastList = new ArrayList<>();
  String baseURL = "https://api.openweathermap.org/data/2.5/";

  private WeatherForecastAdapter adapter;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_details);
    String city = getIntent().getStringExtra("city");
    getCoordinates(city);
//        getData("42.3554334","-71.060511");
    if (savedInstanceState != null) {
      ArrayList<WeatherForecastCard> savedDataList = savedInstanceState.getParcelableArrayList(
          FORECAST_LIST_KEY);
      if (savedDataList != null) {
        forecastList.addAll(savedDataList);
      }
    }
  }

  private void getCoordinates(String city) {
    HTTPcall coordinatesTask = new HTTPcall();
    String URL = "https://api.openweathermap.org/geo/1.0/direct?q=" + city + "&limit=1&appid="
        + OPEN_WEATHER_API_KEY;
    coordinatesTask.execute(URL, "coordinatesTask");
  }

  private void getData(String la, String lo) {
    String lat = la;
    String lon = lo;
    HTTPcall weatherTask = new HTTPcall();
    HTTPcall forecastTask = new HTTPcall();
    try {
      String currentURL = baseURL + "weather?lat=" + lat + "&lon=" + lon + "&units=metric&appid="
          + OPEN_WEATHER_API_KEY;
      String forecastURL = baseURL + "forecast?lat=" + lat + "&lon=" + lon + "&units=metric&appid="
          + OPEN_WEATHER_API_KEY;
      weatherTask.execute(currentURL, "weatherTask");
      forecastTask.execute(forecastURL, "forecastTask");
    } catch (Exception e) {
      Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_SHORT).show();
    }
  }

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

  private void parseData(String[] data) throws JSONException {
    switch (data[1]) {
      case "forecastTask":
        JSONObject jsonObject = new JSONObject(data[0]);
        DateFormat obj = new SimpleDateFormat("E, dd MMM");
        JSONArray jsonArray1 = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArray1.length(); i++) {
          JSONObject day = jsonArray1.getJSONObject(i);
          String temp = day.getJSONObject("main").get("temp") + " ℃";
          Integer dat = (Integer) day.get("dt");
          Date cur = new Date(new Long(dat));
          JSONObject weatherDetails = day.getJSONArray("weather").getJSONObject(0);
          String weather = weatherDetails.getString("main");
          String weatherDesc = weatherDetails.getString("description");
          String weatherIcon = weatherDetails.getString("icon");
          forecastList.add(
              new WeatherForecastCard(temp, obj.format(cur), weather, weatherDesc, weatherIcon));
        }
        createListView();
        break;

      case "weatherTask":
        JSONObject jsonObject2 = new JSONObject(data[0]);
        DateFormat obj2 = new SimpleDateFormat("E, dd MMM");
//                String temp = jsonObject2.getJSONObject("main").get("temp").toString() + "℃";
        Integer dat = (Integer) jsonObject2.get("dt");
        Date cur = new Date(new Long(dat));
        JSONObject weatherDetails = jsonObject2.getJSONArray("weather").getJSONObject(0);
        String weather = weatherDetails.getString("main");
//                String weatherDesc = weatherDetails.getString("description");
        String weatherIcon = weatherDetails.getString("icon");

        TextView city = (TextView) findViewById(R.id.city_in_card);
        TextView date = (TextView) findViewById(R.id.daydate2);
        TextView weatherDesc = (TextView) findViewById(R.id.weather_description2);
        TextView temp = (TextView) findViewById(R.id.temp2);
        TextView wind = (TextView) findViewById(R.id.wind);
        ImageView icon = findViewById(R.id.weather_icon2);

        city.setText((CharSequence) jsonObject2.get("name"));
        date.setText((CharSequence) obj2.format(cur));
        weatherDesc.setText((CharSequence) weatherDetails.getString("description"));
        temp.setText(
            (CharSequence) jsonObject2.getJSONObject("main").get("temp").toString() + " ℃");
        wind.setText(
            (CharSequence) "Wind: " + jsonObject2.getJSONObject("wind").get("speed").toString()
                + " m/s");

        int iconResource = OpenWeatherIconHelper.getWeatherIconImageResource(this, weatherIcon);
        Drawable iconDrawable = ResourcesCompat.getDrawable(getResources(), iconResource, getTheme());
        icon.setImageDrawable(iconDrawable);

        break;
      case "coordinatesTask":
        JSONArray ar = new JSONArray(data[0]);
        JSONObject jsonObject3 = ar.getJSONObject(0);
        getData(jsonObject3.get("lat").toString(), jsonObject3.get("lon").toString());
        break;
      default:
        break;
    }
  }

  private class HTTPcall extends AsyncTask<String, String, String[]> {

    ProgressDialog progressDialog = new ProgressDialog(WeatherForecastDetailsActivity.this);

    @Override
    protected void onPreExecute() {
      Log.i(TAG, "Making progress...");
      progressDialog.setMessage("processing results");
      progressDialog.setCancelable(false);
      progressDialog.show();
    }

    @Override
    protected String[] doInBackground(String... params) {
      String result = "";
      try {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
          url = new URL(params[0]);
          //open a URL coonnection
          urlConnection = (HttpURLConnection) url.openConnection();
          InputStream in = urlConnection.getInputStream();
          InputStreamReader isw = new InputStreamReader(in);
          int data = isw.read();
          while (data != -1) {
            result += (char) data;
            data = isw.read();
          }
          // return the data to onPostExecute method
          return new String[]{result, params[1]};

        } catch (MalformedURLException e) {
          Log.e(TAG, "MalformedURLException");
          e.printStackTrace();
        } catch (ProtocolException e) {
          Log.e(TAG, "ProtocolException");
          e.printStackTrace();
        } catch (IOException e) {
          Log.e(TAG, "IOException");
          e.printStackTrace();
        }
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
      return new String[]{};
    }

    @Override
    protected void onPostExecute(String s[]) {
      progressDialog.dismiss();
      try {
        parseData(s);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
}
