package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

/**
 * WeatherCard -- represents the object of what we will receive from the OpenWeatherMap API
 * response.
 */
public class WeatherCard {

  private final String locationName;
  private final String temperature;
  private final String icon;

  private final String latitude;
  private final String longitude;

  /**
   * Five argument constructor for WeatherCard. Will set the locationName, country, state for the
   * response we receive from the API call.
   *
   * @param locationName the name of the city.
   * @param temperature  the current temperature in the city.
   * @param icon         the icon for the weather in the city.
   */
  public WeatherCard(String locationName, String temperature, String icon, String lat, String lon) {
    this.locationName = locationName;
    this.temperature = temperature;
    this.icon = icon;
    this.latitude = lat;
    this.longitude = lon;
  }

  public String getLocationName() {
    return locationName;
  }

  public String getTemperature() {
    return temperature;
  }

  public String getIcon() {
    return icon;
  }

  public String getLatitude() { return latitude; }

  public String getLongitude() {return longitude; }
}
