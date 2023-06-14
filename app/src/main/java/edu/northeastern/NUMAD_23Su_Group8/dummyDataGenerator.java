package edu.northeastern.NUMAD_23Su_Group8;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class dummyDataGenerator {

    public static List<WeatherCard> generateData(Context context) {
        List<WeatherCard> data = new ArrayList<>();
        try {

            // opening the JSON file to read data from it.
            InputStream is = context.getAssets().open("dummyData.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            // reading data then closing the input stream.
            is.read(buffer);
            is.close();

            // storing results into a json array to loop through.
            String jsonResults = new String(buffer, "UTF-8");
            JSONArray jsonArr = new JSONArray(jsonResults);

            // looping through the json array, creating WeatherCard objects programmatically then
            // adding to the List.
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                WeatherCard weatherCard = new WeatherCard(jsonObject.getString("locationName"),
                        jsonObject.getString("state"), jsonObject.getString("country"));
                data.add(weatherCard);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
