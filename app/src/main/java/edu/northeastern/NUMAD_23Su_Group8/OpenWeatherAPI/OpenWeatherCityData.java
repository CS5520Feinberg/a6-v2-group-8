package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.annotation.SuppressLint;

public class OpenWeatherCityData {

  private final String cityName;
  private final String temperature;
  private final String icon;

  @SuppressLint("DefaultLocale")
  public OpenWeatherCityData(String cityName, String temperature, String icon) {
    this.cityName = cityName;

    Double tempDouble = Double.parseDouble(temperature) - 273.15;
    this.temperature = String.format("%.1f ℃", tempDouble);

    this.icon = icon;
  }


  public String getCityName() {
    return this.cityName;
  }

  public String getTemperature() {
    return this.temperature;
  }

  public String getIcon() {
    return this.icon;
  }
}
