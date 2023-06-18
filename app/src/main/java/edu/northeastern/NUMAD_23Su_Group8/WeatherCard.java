package edu.northeastern.NUMAD_23Su_Group8;

/**
 * WeatherCard -- represents the object of what we will receive from the OpenWeatherMap API
 * response.
 */
public class WeatherCard {

  private String locationName;
  private String temperature;
  private int latitude;
  private int longitude;

  /**
   * Five argument constructor for WeatherCard. Will set the locationName, country, state for the
   * response we receive from the API call.
   *
   * @param locationName
   * @param temperature
   */
  public WeatherCard(String locationName, String temperature) {
    this.locationName = locationName;
    this.temperature = temperature;
  }

  public String getLocationName() {
    return locationName;
  }

  public void setLocationName(String locationName) {
    this.locationName = locationName;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public int getLatitude() {
    return latitude;
  }

  public void setLatitude(int latitude) {
    this.latitude = latitude;
  }

  public int getLongitude() {
    return longitude;
  }

  public void setLongitude(int longitude) {
    this.longitude = longitude;
  }
}
