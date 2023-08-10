package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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

        this.messagesRecyclerView = binding.messagesRecyclerView;
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


        DatabaseReference conversationRef = messagingRepository.getFirebaseDbHandler()
            .getDbInstance()
            .getReference()
            .child("messages")
            .child(partnerUserName)
            .child(messagingRepository.getCurrentUser(handler, this));

        conversationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<MessageCard> messageCards = new ArrayList<>();

                for (DataSnapshot messageSnapshot : snapshot.getChildren()) {
                    MessageCard messageCard = messageSnapshot.getValue(MessageCard.class);
                    messageCards.add(messageCard);
                }

                // Create a new instance of the adapter with the retrieved message cards
                MessageRecyclerViewAdapter adapter = new MessageRecyclerViewAdapter(messageCards);

                // Set the adapter on the RecyclerView
                messagesRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // leaving empty
            }
        });

        button1.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_1;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
            messageRecyclerViewAdapter.notifyDataSetChanged();


        });

        button2.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_2;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button3.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_3;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button4.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_4;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button5.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_5;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button6.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_6;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button7.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_7;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);

            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });

        button8.setOnClickListener(v -> {

            long timestamp = System.currentTimeMillis();
            boolean sent = true;
            int stickerResourceId = R.drawable.sticker_8;
            MessageCard messageCard = new MessageCard(stickerResourceId, timestamp, sent);
            List<MessageCard> messageCards = new ArrayList<>();

            messageCards.add(messageCard);
            messagingRepository.addMessageToDb(handler, this, partnerUserName, messageCard);

            messageRecyclerViewAdapter.addMessageCards(messageCards);
            int lastItem = messageRecyclerViewAdapter.getItemCount() - 1;
            messagesRecyclerView.scrollToPosition(lastItem);
        });


    }



}