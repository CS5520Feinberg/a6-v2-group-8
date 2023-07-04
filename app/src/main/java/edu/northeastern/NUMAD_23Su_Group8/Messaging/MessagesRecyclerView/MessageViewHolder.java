package edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.NUMAD_23Su_Group8.R;

public class MessageViewHolder extends RecyclerView.ViewHolder{

    private final TextView sentFlag;
    private final TextView timeStamp;
    private final CardView messageCard;
    private final ImageView stickerCard;


    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.sentFlag = itemView.findViewById(R.id.sentFlag);
        this.timeStamp = itemView.findViewById(R.id.timeStamp);
        this.messageCard = itemView.findViewById(R.id.messageCard);
        this.stickerCard = itemView.findViewById(R.id.stickerCard);
    }

    public TextView getSentFlag() {
        return sentFlag;
    }

    public TextView getTimeStamp() {
        return timeStamp;
    }

    public CardView getMessageCard() {
        return messageCard;
    }

    public ImageView getStickerCard() {
        return stickerCard;
    }
}
