package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class OpenWeatherCities {

  private static Map<String, OpenWeatherCity> cities;

  private static void initCitiesMap(Context context) {
    cities = new HashMap<>();
    try {

      // opening the JSON file to read data from it.
      InputStream is = context.getAssets().open("cities_with_coordinates.json");
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

        OpenWeatherCity weatherCard = new OpenWeatherCity(jsonObject.getString("name"),
            jsonObject.getString("coor_lat"), jsonObject.getString("coor_long"));

        cities.put(jsonObject.getString("name"), weatherCard);
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
    if (cities == null) {
      initCitiesMap(context);
    }

    if (!cities.containsKey(cityName)) {
      throw new NotFoundException("City not found in list!");
    }

    return cities.get(cityName);
  }
}
