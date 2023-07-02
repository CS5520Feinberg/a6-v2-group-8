package edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherIconHelper;
import edu.northeastern.NUMAD_23Su_Group8.R;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.CardClickListener;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.WeatherCard;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.WeatherViewHolder;
import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserViewHolder> {

  private Context parentContext;
  private List<UserCard> userCardList;
  private UserCardClickListener listener;

  public UserRecyclerViewAdapter(List<UserCard> userCardList) {
    this.userCardList = userCardList;
  }

  @Override
  public int getItemCount() {
    return this.userCardList.size();
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    this.parentContext = parent.getContext();
    View view = LayoutInflater.from(this.parentContext)
        .inflate(R.layout.card_user, parent, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    holder.getUserName().setText(userCardList.get(position).getUserName());

    // pass the selected city name through the listener to weather details activity
    holder.getChatBtn().setOnClickListener(v -> {
      int currentPosition = holder.getAdapterPosition();
      if (currentPosition != RecyclerView.NO_POSITION) {
        listener.onChatClick(userCardList.get(position).getUserName());
      }
    });
  }

  public void setUserCardClickListener(UserCardClickListener listener) {
    this.listener = listener;
  }
}
