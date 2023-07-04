package edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.northeastern.NUMAD_23Su_Group8.R;

public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private Context parentContext;

    private List<MessageCard> messageCardList;

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

    }

    @Override
    public int getItemCount() {
        return this.messageCardList.size();
    }
}
