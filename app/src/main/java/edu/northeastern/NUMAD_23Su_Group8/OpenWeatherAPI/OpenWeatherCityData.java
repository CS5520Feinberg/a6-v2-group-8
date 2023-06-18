package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.annotation.SuppressLint;

public class OpenWeatherCityData {

  private final String cityName;
  private final String temperature;

  @SuppressLint("DefaultLocale")
  public OpenWeatherCityData(String cityName, String temperature) {
    this.cityName = cityName;
    String temperatureString = temperature;

    Double tempDouble = Double.parseDouble(temperatureString) - 273.15;
    this.temperature = String.format("%.2f °C", tempDouble);
  }


  public String getCityName() {
    return cityName;
  }

  public String getTemperature() {
    return temperature;
  }
}
