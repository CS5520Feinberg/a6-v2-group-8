package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.util.Log;
import android.util.Patterns;
import android.webkit.URLUtil;
import edu.northeastern.NUMAD_23Su_Group8.BuildConfig;
import edu.northeastern.NUMAD_23Su_Group8.WeatherCard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherRequestsHelper {

  private final static String baseURL = "https://api.openweathermap.org/data/2.5/";
  private final static String WEATHER_URL = "https://api.openweathermap.org/geo/1.0/reverse?";
  private final static String API_KEY = BuildConfig.OPEN_WEATHER_API_KEY;

  public static String getCityWeather(OpenWeatherCity city) {
    try {

      String urlString = String.format(WEATHER_URL + "lat=%s&lon=%s&appid=%s", city.getLatitude(),
          city.getLongitude(), API_KEY);
      URL url = new URL(urlString);
      String resp = httpResponse(url);

      JSONObject resultJSON = new JSONObject(resp);

      JSONObject coordinatesJSON = resultJSON.getJSONObject("coord");
      String latitude = coordinatesJSON.getString("lat");
      String longitude = coordinatesJSON.getString("lon");

      String name = resultJSON.getString("name");

      JSONArray weatherJSONArray = resultJSON.getJSONArray("weather");

      String base = resultJSON.getString("base");

      JSONObject mainJSON = resultJSON.getJSONObject("main");
      String temperature = mainJSON.getString("temp");
      String feels_like = mainJSON.getString("feels_like");
      String temp_min = mainJSON.getString("temp_min");
      String temp_max = mainJSON.getString("temp_max");
      String pressure = mainJSON.getString("pressure");
      String humidity = mainJSON.getString("humidity");
      String sea_level = mainJSON.getString("sea_level");
      String ground_level = mainJSON.getString("grnd_level");

      Integer visibility = resultJSON.getInt("visibility");

      JSONObject windJSON = resultJSON.getJSONObject("wind");
      Double speed = windJSON.getDouble("speed");
      String deg = windJSON.getString("deg");
      String gust = windJSON.getString("gust");

      JSONObject cloudsJSON = resultJSON.getJSONObject("clouds");
      String clouds_all = cloudsJSON.getString("all");

      String date = resultJSON.getString("dt");

      String timezone = resultJSON.getString("timezone");

      return name;

      // TODO: return entire weather object

    } catch (JSONException | IOException e) {
      e.printStackTrace();
    }

    return null;
  }


  public static class MyException extends Exception {

    public MyException() {
    }

    public MyException(String message) {
      super(message);
    }
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
