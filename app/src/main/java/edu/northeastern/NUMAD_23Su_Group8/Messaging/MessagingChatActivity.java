package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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

    private MessagingRepository messagingRepository;


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

        messagingRepository = MessagingRepository.getInstance();

        Intent intent = getIntent();

        String partnerUserName = intent.getStringExtra("userName");




        ImageButton button1 = binding.stickerOne;
        ImageButton button2 = binding.stickerTwo;
        ImageButton button3 = binding.stickerThree;
        ImageButton button4 = binding.stickerFour;
        ImageButton button5 = binding.stickerFive;
        ImageButton button6 = binding.stickerSix;
        ImageButton button7 = binding.stickerSeven;
        ImageButton button8 = binding.stickerEight;

        button1.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_1;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);

            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);


        });

        button2.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_2;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button3.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_3;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button4.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_4;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button5.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_5;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button6.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_6;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button7.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_7;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button8.setOnClickListener(v -> {
            // TODO put to 'conversations' database, add to recycler view.

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_8;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            messageRecyclerViewAdapter.addMessageCard(messageCard);

            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });








    }


}