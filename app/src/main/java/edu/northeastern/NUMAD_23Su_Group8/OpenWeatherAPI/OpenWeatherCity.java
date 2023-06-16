package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

class OpenWeatherCity {

  private String name;
  private String latitude;
  private String longitude;

  public OpenWeatherCity(String name, String latitude, String longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getName() {
    return name;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }
}
