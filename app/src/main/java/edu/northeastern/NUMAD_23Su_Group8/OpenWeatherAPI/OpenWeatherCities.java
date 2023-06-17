package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenWeatherCities {

  private static List<String> citiesList;
  private static Map<String, OpenWeatherCity> citiesMap;

  private static void initCitiesMap(Context context) {
    citiesMap = new HashMap<>();
    citiesList = new ArrayList<>();
    try {

      // opening the JSON file to read data from it.
      InputStream is = context.getAssets().open("cities_with_coordinates_mini.json");
      int size = is.available();
      byte[] buffer = new byte[size];

      // reading data then closing the input stream.
      is.read(buffer);
      is.close();

      // storing results into a json array to loop through.
      String jsonResults = new String(buffer, StandardCharsets.UTF_8);
      JSONArray jsonArr = new JSONArray(jsonResults);

      // looping through the json array, creating WeatherCard objects programmatically then
      // adding to the List.
      for (int i = 0; i < jsonArr.length(); i++) {
        JSONObject jsonObject = jsonArr.getJSONObject(i);
        String cityName = jsonObject.getString("Name");

        OpenWeatherCity weatherCard = new OpenWeatherCity(jsonObject.getString("Name"),
            jsonObject.getString("coor_lat"), jsonObject.getString("coor_long"));

        citiesMap.put(cityName, weatherCard);
        citiesList.add(cityName);
      }

    } catch (IOException | JSONException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets city object with name, latitude, and longitude.
   *
   * @param context  context for android asset access.
   * @param cityName Name of the city
   * @return city object.
   */
  public static OpenWeatherCity getCity(Context context, String cityName) {
    if (citiesMap == null) {
      initCitiesMap(context);
    }

    if (!citiesMap.containsKey(cityName)) {
      throw new NotFoundException("City not found in list!");
    }

    return citiesMap.get(cityName);
  }

  public static List<String> getCityList(Context context) {
    if (citiesList == null) {
      initCitiesMap(context);
    }

    return citiesList;
  }
}
