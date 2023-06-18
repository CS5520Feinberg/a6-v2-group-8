package edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI;

import android.content.Context;

public class OpenWeatherIconHelper {
  public static int getWeatherIconImageResource(Context context, String icon) {
    String uri = String.format("@drawable/_%s", icon);
    return context.getResources().getIdentifier(uri, null, context.getPackageName());
  }
}
