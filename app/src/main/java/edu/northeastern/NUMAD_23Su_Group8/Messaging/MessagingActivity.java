package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserCard;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserCardClickListener;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserRecyclerViewAdapter;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherCities;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherCity;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherCityData;
import edu.northeastern.NUMAD_23Su_Group8.OpenWeatherAPI.OpenWeatherRequestsHelper;
import edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.Entities.User;
import edu.northeastern.NUMAD_23Su_Group8.R;
import edu.northeastern.NUMAD_23Su_Group8.Weather.RecyclerView.WeatherCard;
import edu.northeastern.NUMAD_23Su_Group8.Weather.WebServiceActivity;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingBinding;
import java.util.ArrayList;
import java.util.List;

public class MessagingActivity extends AppCompatActivity {

  private static final String TAG = "_MessagingActivity";
  private final Handler handler = new Handler();
  private RecyclerView usersRecyclerView;
  private UserRecyclerViewAdapter userRecyclerViewAdapter;
  private MessagingRepository messagingRepository;
  private ProgressBar progressBar;

  private void setupRecyclerView(ActivityMessagingBinding binding) {
    // setting this as fixed for now, but if we need to adapt this later we can.
    this.usersRecyclerView = binding.usersRecyclerView;

    this.usersRecyclerView.setHasFixedSize(true);
    this.usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  private void setupRecyclerViewListenerAndAdapter() {
    // set up a listener for card click
    UserCardClickListener userCardClickListener = userName -> {
      Intent intent = new Intent(MessagingActivity.this, MessagingChatActivity.class);
      intent.putExtra("userName", userName);
      startActivity(intent);
    };

    this.userRecyclerViewAdapter.setUserCardClickListener(userCardClickListener);
    this.usersRecyclerView.setAdapter(this.userRecyclerViewAdapter);
  }

  private ChildEventListener getUserChildEventListener(Context activityContext) {
    return new ChildEventListener() {

      @Override
      public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        String userName = snapshot.getKey();

        MessagingActivity.this.userRecyclerViewAdapter.addCard(new UserCard(userName));
      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot snapshot,
          @Nullable String previousChildName) {
        // user will never change

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot snapshot) {
        String userName = snapshot.getKey();
        MessagingActivity.this.userRecyclerViewAdapter.removeCard(userName);
      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        // user will never get moved
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        Log.e(TAG, "onCancelled:" + error);
        Toast.makeText(activityContext
            , "DBError: " + error, Toast.LENGTH_SHORT).show();
      }
    };
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingBinding binding = ActivityMessagingBinding.inflate(
        getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);

    progressBar = findViewById(R.id.userListProgressBar);

    List<UserCard> userCardList = new ArrayList<>();

    this.setupRecyclerView(binding);

    this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(userCardList);
    this.setupRecyclerViewListenerAndAdapter();

    messagingRepository = MessagingRepository.getInstance();
  }

  @Override
  protected void onResume() {
    super.onResume();

    runOnUiThread(() -> progressBar.setVisibility(View.VISIBLE));

    this.messagingRepository.loadUserList(this.handler, this.userRecyclerViewAdapter, this.progressBar);

    this.messagingRepository.addUsersChildEventListener(this.getUserChildEventListener(this));
  }

  @Override
  protected void onPause() {
    super.onPause();
    this.messagingRepository.removeUsersChildEventListener();
  }
}
