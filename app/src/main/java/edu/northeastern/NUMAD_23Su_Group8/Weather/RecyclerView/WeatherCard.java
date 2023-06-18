package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

/**
 * WeatherCard -- represents the object of what we will receive from the OpenWeatherMap API
 * response.
 */
public class WeatherCard {

  private final String locationName;
  private final String temperature;
  private final String icon;

  /**
   * Five argument constructor for WeatherCard. Will set the locationName, country, state for the
   * response we receive from the API call.
   *
   * @param locationName the name of the city.
   * @param temperature  the current temperature in the city.
   * @param icon         the icon for the weather in the city.
   */
  public WeatherCard(String locationName, String temperature, String icon) {
    this.locationName = locationName;
    this.temperature = temperature;
    this.icon = icon;
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
}
