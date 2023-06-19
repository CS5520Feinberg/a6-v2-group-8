package edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView;

/**
 * To listen to city selection in the list to get the more details and forecast
 */
public interface CardClickListener {
    void onSeeMoreClick(String lat, String lon);
}
