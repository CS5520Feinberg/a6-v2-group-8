package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.FirebaseDBHandler;
import java.util.HashMap;
import android.os.Handler;

public class MessagingRepository {

  private static final String TAG = "_MessagingRepository";
  private final Handler activityThreadHandler;
  private final Context activityContext;
  private final FirebaseDBHandler firebaseDbHandler;

  private ChildEventListener getUserChildEventListener(Context activityContext) {
    return new ChildEventListener() {

      @Override
      public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

      }

      @Override
      public void onChildChanged(@NonNull DataSnapshot snapshot,
          @Nullable String previousChildName) {

      }

      @Override
      public void onChildRemoved(@NonNull DataSnapshot snapshot) {

      }

      @Override
      public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        Log.e(TAG, "onCancelled:" + error);
        Toast.makeText(activityContext
            , "DBError: " + error, Toast.LENGTH_SHORT).show();
      }
    };
  }

  public MessagingRepository(Handler handler, Context activityContext) {
    this.activityThreadHandler = handler;
    this.activityContext = activityContext;
    this.firebaseDbHandler = new FirebaseDBHandler(this.getUserChildEventListener(activityContext));
  }

  public void registerUser(String userName) {
    firebaseDbHandler.getDbInstance().getReference().child("users").get()
        .addOnCompleteListener(task -> {
          if (!task.isSuccessful()) {
            Log.e("firebase", "Error getting data", task.getException());
          } else {
            HashMap value = (HashMap) task.getResult().getValue();
            boolean flag = true;
            for (Object key : value.keySet()) {
              if (key.toString().equals(userName)) {
                flag = false;
              }
            }

            if (flag) {
              Log.i(TAG, String.format("User %s being added to database", userName));
              this.firebaseDbHandler.addUserToDb(userName);

              Log.i(TAG, String.format("User %s being logged in", userName));
              this.activityThreadHandler.post(() -> {
                Intent intent = new Intent(this.activityContext, MessagingChatActivity.class);
                this.activityContext.startActivity(intent);
              });
            } else {
              Log.i(TAG, String.format("User %s already exists", userName));
              this.activityThreadHandler.post(
                  () -> Toast.makeText(this.activityContext, "User already exists! ",
                      Toast.LENGTH_SHORT).show());
            }
          }
        });
  }

  public void loginUser(String userName) {
    firebaseDbHandler.getDbInstance().getReference().child("users").get()
        .addOnCompleteListener(task -> {
          if (!task.isSuccessful()) {
            Log.e(TAG, "Error getting data", task.getException());
          } else {
            HashMap value = (HashMap) task.getResult().getValue();
            boolean flag = true;
            for (Object key : value.keySet()) {
              if (key.toString().equals(userName)) {
                flag = false;
              }
            }

            if (!flag) {
              Log.i(TAG, String.format("User %s being logged in", userName));
              this.activityThreadHandler.post(() -> {
                Intent intent = new Intent(this.activityContext, MessagingChatActivity.class);
                this.activityContext.startActivity(intent);
              });
            } else {
              Log.i(TAG, String.format("User %s being added to database", userName));
              this.activityThreadHandler.post(() -> Toast.makeText(this.activityContext,
                  "User does no exist, please register. ", Toast.LENGTH_SHORT).show());
            }
          }
        });
  }
}
