package edu.northeastern.NUMAD_23Su_Group8;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class WeatherForecastCard  implements Parcelable {

    private int temp;
    private String currentDate;
    private String weather;
    private String weatherDescription;

    public WeatherForecastCard(int temp, String currentDate, String weather, String weatherDescription) {
        this.temp = temp;
        this.weather = weather;
        this.currentDate = currentDate;
        this.weatherDescription =weatherDescription;
    }

    protected WeatherForecastCard(Parcel in) {
        temp = in.readInt();
        weather = in.readString();
        currentDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(temp);
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
