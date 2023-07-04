package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView.MessageCard;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView.MessageRecyclerViewAdapter;
import edu.northeastern.NUMAD_23Su_Group8.R;
import edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingChatBinding;

public class MessagingChatActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private RecyclerView messagesRecyclerView;
    private MessageRecyclerViewAdapter messageRecyclerViewAdapter;


    private void setupRecyclerView(ActivityMessagingChatBinding binding) {
        this.messagesRecyclerView = binding.messagesRecyclerView;
        this.messagesRecyclerView.setHasFixedSize(true);
        this.messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging_chat);
        edu.northeastern.NUMAD_23Su_Group8.databinding.ActivityMessagingChatBinding binding = ActivityMessagingChatBinding.inflate(
                getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<MessageCard> messageCardList = new ArrayList<>();
        this.setupRecyclerView(binding);
        this.messageRecyclerViewAdapter = new MessageRecyclerViewAdapter(messageCardList);
        this.messagesRecyclerView.setAdapter(this.messageRecyclerViewAdapter);


        Button button1 = binding.stickerOne;

    }


}