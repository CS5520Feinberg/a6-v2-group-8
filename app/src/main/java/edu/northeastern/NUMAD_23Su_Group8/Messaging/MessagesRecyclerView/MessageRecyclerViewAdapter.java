package edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.northeastern.NUMAD_23Su_Group8.R;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private Context parentContext;

    private List<MessageCard> messageCardList;

    private int imageResourceId = R.drawable._01d;

    public MessageRecyclerViewAdapter(List<MessageCard> messageCardList) {
        this.messageCardList = messageCardList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parentContext = parent.getContext();
        View view = LayoutInflater.from(this.parentContext)
                .inflate(R.layout.card_message, parent, false);

        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {

        MessageCard messageCard = messageCardList.get(position);
        holder.getStickerCard().setImageResource(messageCard.getStickerId());
        TextView sentFlagTV = holder.itemView.findViewById(R.id.sentFlag);
        TextView timestampTV = holder.itemView.findViewById(R.id.timeStamp);

        // formatting and setting the timestamp.
        long timestamp = messageCard.getTimestamp();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String formattedTS = sdf.format(new Date(timestamp));


        // formatting the Text View depending on whether the sticker was sent or received.
        if (messageCard.isSentByUser()) {
            sentFlagTV.setText("Sent!");
            timestampTV.setText("Sticker was sent at: " + formattedTS);
        } else {
            sentFlagTV.setText("Received!");
            timestampTV.setText("Sticker was received at: " + formattedTS);
        }

    }


    @Override
    public int getItemCount() {
        return this.messageCardList.size();
    }
    public void updateImageResource(int resourceId) {
        imageResourceId = resourceId;
        notifyDataSetChanged();
    }

    public void addMessageCard(MessageCard messageCard) {
        messageCardList.add(messageCard);
        notifyDataSetChanged();
    }


}
