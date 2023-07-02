package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserCard;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserCardClickListener;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserRecyclerViewAdapter;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherCities;
import edu.northeastern.NUMAD_23Su_Group8.R;
import edu.northeastern.NUMAD_23Su_Group8.Weather.DetailedView.WeatherForecastDetailsActivity;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.CardClickListener;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.WeatherCard;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.WeatherRecyclerViewAdapter;
import edu.northeastern.NUMAD_23Su_Group8.Weather.WebServiceActivity;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingBinding;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityWebServiceBinding;
import java.util.ArrayList;
import java.util.List;

public class MessagingActivity extends AppCompatActivity {

    private RecyclerView usersRecyclerView;
    private UserRecyclerViewAdapter userRecyclerViewAdapter;

    private void setupRecyclerView(ActivityMessagingBinding binding) {
        // setting this as fixed for now, but if we need to adapt this later we can.
        this.usersRecyclerView = binding.usersRecyclerView;

        this.usersRecyclerView.setHasFixedSize(true);
        this.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupRecyclerViewListenerAndAdapter() {
        // set up a listener for city card click
        UserCardClickListener userCardClickListener = userName -> {
            Intent intent = new Intent(MessagingActivity.this, MessagingChatActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
        };

        this.userRecyclerViewAdapter.setUserCardClickListener(userCardClickListener);
        this.usersRecyclerView.setAdapter(this.userRecyclerViewAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingBinding binding = ActivityMessagingBinding.inflate(
            getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<UserCard> userCardList = new ArrayList<>();

        this.setupRecyclerView(binding);

        this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(userCardList);
        this.setupRecyclerViewListenerAndAdapter();
    }
}
