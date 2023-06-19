package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class for weatherforecast card data
 */
public class WeatherForecastCard implements Parcelable {

  private final String temperature;
  private final String currentDate;
  private final String weather;
  private String weatherDescription;
  private String weatherIcon;

  public WeatherForecastCard(String temp, String currentDate, String weather,
      String weatherDescription, String weatherIcon) {
    this.temperature = temp;
    this.weather = weather;
    this.currentDate = currentDate;
    this.weatherDescription = weatherDescription;
    this.weatherIcon = weatherIcon;
  }

  protected WeatherForecastCard(Parcel in) {
    temperature = in.readString();
    weather = in.readString();
    currentDate = in.readString();
  }

  public String getTemperature() {
    return temperature;
  }

  public String getWeather() {
    return weather;
  }

  public String getWeatherDescription() {
    return weatherDescription;
  }

  public String getCurrentDate() {
    return currentDate;
  }

  public String getWeatherIcon() {
    return weatherIcon;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(temperature);
    dest.writeString(weather);
    dest.writeString(currentDate);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<WeatherForecastCard> CREATOR = new Creator<WeatherForecastCard>() {
    @Override
    public WeatherForecastCard createFromParcel(Parcel in) {
      return new WeatherForecastCard(in);
    }

    @Override
    public WeatherForecastCard[] newArray(int size) {
      return new WeatherForecastCard[size];
    }
  };
}
