package edu.northeastern.NUMAD_23Su_Group8;

import static android.content.ContentValues.TAG;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView forecastListRecyclerView;
    private static final String FORECAST_LIST_KEY = "forecast_list";
    List<WeatherForecastCard> forecastList = new ArrayList<>();

    private WeatherForecastAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        HTTPcall task = new HTTPcall();
        try {
            String url = HTTpUtils.validInput("https://api.openweathermap.org/data/2.5/forecast?lat=44.34&lon=10.99&appid=60477ecb4e02a854557beda9abdb39b4");
            task.execute(url); // This is a security risk.  Don't let your user enter the URL in a real app.
        } catch (HTTpUtils.MyException e) {
            Toast.makeText(getApplication(),e.toString(),Toast.LENGTH_SHORT).show();
        }

        if (savedInstanceState != null) {
            ArrayList<WeatherForecastCard> savedDataList = savedInstanceState.getParcelableArrayList(FORECAST_LIST_KEY);
            if (savedDataList != null) {
                forecastList.addAll(savedDataList);
            }
        }
    }

    public void createRecyclerView() {
        forecastListRecyclerView = findViewById(R.id.forecastList);
        adapter = new WeatherForecastAdapter(forecastList, this);
        forecastListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        forecastListRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(FORECAST_LIST_KEY, new ArrayList<>(forecastList));
    }

    private void parseData(String data) throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        DateFormat obj = new SimpleDateFormat(" E, dd MMM yyyy");
        JSONArray jsonArray1 = jsonObject.getJSONArray("list");
        for(int i=0; i<jsonArray1.length(); i++) {
            JSONObject day = jsonArray1.getJSONObject(i);
            Double temp = (Double) day.getJSONObject("main").get("temp");
            Integer dat = (Integer) day.get("dt");
            Date cur = new Date(new Long(dat));
            JSONObject weatherDetails = day.getJSONArray("weather").getJSONObject(0);
            String weather = weatherDetails.getString("main");
            String weatherDesc = weatherDetails.getString("description");
            forecastList.add(new WeatherForecastCard( temp, obj.format(cur), weather,weatherDesc));
        }
        createRecyclerView();
    }

    private class HTTPcall extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject jObject = new JSONObject();
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
                    return result;

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
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                parseData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
