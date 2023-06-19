package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.annotation.SuppressLint;

public class OpenWeatherCityData {

  private final String cityName;
  private final String temperature;
  private final String icon;

  private final String latitude;
  private final String longitude;

  @SuppressLint("DefaultLocale")
  public OpenWeatherCityData(String cityName, String temperature, String icon, String lat, String lon) {
    this.cityName = cityName;

    Double tempDouble = Double.parseDouble(temperature) - 273.15;
    this.temperature = String.format("%.1f ℃", tempDouble);
    this.latitude = lat;
    this.longitude = lon;

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
  public String getLatitude() { return  this.latitude;};
  public String getLongitude() { return this.longitude;}


}
