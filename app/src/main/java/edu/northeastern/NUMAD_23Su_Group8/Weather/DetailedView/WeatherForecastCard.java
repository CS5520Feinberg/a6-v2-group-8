package edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherForecastCard  implements Parcelable {

    private String temp;
    private String currentDate;
    private String weather;
    private String weatherDescription;
    private String weatherIcon;

    public WeatherForecastCard(String temp, String currentDate, String weather, String weatherDescription, String weatherIcon) {
        this.temp = temp;
        this.weather = weather;
        this.currentDate = currentDate;
        this.weatherDescription =weatherDescription;
        this.weatherIcon = weatherIcon;
    }

    protected WeatherForecastCard(Parcel in) {
        temp = in.readString();
        weather = in.readString();
        currentDate = in.readString();
    }

    public String getTemp() {
        return temp;
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

    public String getWeatherIcon() { return weatherIcon;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temp);
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
