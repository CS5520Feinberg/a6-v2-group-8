package edu.northeastern.NUMAD_23Su_Group8;

/**
 * WeatherCard -- represents the object of what we will receive from the OpenWeatherMap API response.
 *
 */
public class WeatherCard {

    private String locationName;
    private String country;
    private String state;
    private int latitude;
    private int longitude;

    /**
     * Five argument constructor for WeatherCard. Will set the locationName, country, state, lat, and
     * lon for the response we receive from the API call.
     * @param locationName
     * @param country
     * @param state
     * @param latitude
     * @param longitude
     */
    public WeatherCard(String locationName, String country, String state, int latitude, int longitude) {
        this.locationName = locationName;
        this.country = country;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
