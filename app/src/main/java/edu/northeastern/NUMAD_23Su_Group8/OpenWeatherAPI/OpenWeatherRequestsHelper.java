package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.content.Context;
import android.util.Log;
import edu.northeastern.NUMAD_23Su_Group8.BuildConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherRequestsHelper {

  /**
   * API Endpoint URI for getting latitude, longitude, state and country for city.
   */
  private final static String REVERSE_URL = "https://api.openweathermap.org/geo/1.0/reverse?";
  /**
   * API Endpoint URI for current day weather.
   */
  private final static String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?";
  /**
   * API Key for OpenWeather API - fetched dynamically and securely from BuildConfig.
   */
  private final static String API_KEY = BuildConfig.OPEN_WEATHER_API_KEY;

  public static OpenWeatherCityData getCityWeather(OpenWeatherCity city) {
    try {

      String urlString = String.format(WEATHER_URL + "lat=%s&lon=%s&appid=%s", city.getLatitude(),
          city.getLongitude(), API_KEY);
      URL url = new URL(urlString);
      String resp = httpResponse(url);

      // NOTE: There are many more fields in this response, but we are taking only what we need

      JSONObject resultJSON = new JSONObject(resp);

      String name = resultJSON.getString("name");

      JSONArray weatherJSONArray = resultJSON.getJSONArray("weather");
      JSONObject weatherJSON = weatherJSONArray.getJSONObject(0);

      String icon = weatherJSON.getString("icon");

      JSONObject mainJSON = resultJSON.getJSONObject("main");
      String temperature = mainJSON.getString("temp");

      return new OpenWeatherCityData(name, temperature, icon, city.getLatitude(), city.getLongitude());

    } catch (JSONException | IOException e) {
      Log.e("Request Helper", e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  private static String httpResponse(URL url) throws IOException {
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setDoInput(true);

    conn.connect();

    // Read response.
    InputStream inputStream = conn.getInputStream();
    String resp = convertStreamToString(inputStream);

    return resp;
  }

  private static String convertStreamToString(InputStream inputStream) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
      String len;
      while ((len = bufferedReader.readLine()) != null) {
        stringBuilder.append(len);
      }
      bufferedReader.close();
      return stringBuilder.toString().replace(",", ",\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
}
