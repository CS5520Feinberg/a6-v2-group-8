package edu.northeastern.NUMAD_23Su_Group8;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherForecastCard  implements Parcelable {

    private Double temp;
    private String currentDate;
    private String weather;
    private String weatherDescription;

    public WeatherForecastCard(Double temp, String currentDate, String weather, String weatherDescription) {
        this.temp = temp;
        this.weather = weather;
        this.currentDate = currentDate;
        this.weatherDescription =weatherDescription;
    }

    protected WeatherForecastCard(Parcel in) {
        temp = in.readDouble();
        weather = in.readString();
        currentDate = in.readString();
    }

    public String getTemp() {
        return temp.toString();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
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
