package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import edu.northeastern.NUMAD_23Su_Group8.R;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingBinding;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingChatBinding;

public class MessagingChatActivity extends AppCompatActivity {

    private RecyclerView messagesRecyclerView;
    private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_chat);
        edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingChatBinding binding = ActivityMessagingChatBinding.inflate(
                getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}