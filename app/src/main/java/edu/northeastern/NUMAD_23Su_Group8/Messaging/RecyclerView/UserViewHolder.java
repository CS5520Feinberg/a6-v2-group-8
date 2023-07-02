package edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.northeastern.NUMAD_23Su_Group8.R;

public class UserViewHolder  extends RecyclerView.ViewHolder{
  private final TextView userName;
  private final Button chatBtn;

  public UserViewHolder(@NonNull View itemView) {
    super(itemView);
    this.userName = itemView.findViewById(R.id.txt_userName);
    this.chatBtn = itemView.findViewById(R.id.btn_chat);
  }

  public TextView getUserName() {
    return this.userName;
  }

  public Button getChatBtn() {
    return this.chatBtn;
  }
}
