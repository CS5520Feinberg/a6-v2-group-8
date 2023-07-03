package edu.northeastern.NUMAD_23Su_Group8.Messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView.UserRecyclerViewAdapter;
import edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.FirebaseDBHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessagingRepository {

  private static final String TAG = "_MessagingRepository";
  private final static FirebaseDBHandler firebaseDbHandler = new FirebaseDBHandler();

  private static ChildEventListener usersChildEventListener;

  private static MessagingRepository INSTANCE;

  private MessagingRepository() {
  }

  public static MessagingRepository getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new MessagingRepository();
    }

    return INSTANCE;
  }

  public void addUsersChildEventListener(ChildEventListener childEventListener) {
    usersChildEventListener = childEventListener;

    firebaseDbHandler.addUsersChildEventListener(usersChildEventListener);
  }

  public void removeUsersChildEventListener() {
    firebaseDbHandler.removeUsersChildEventListener(usersChildEventListener);
  }

  public void loadUserList(Handler handler, UserRecyclerViewAdapter adapter, ProgressBar progressBar) {
    firebaseDbHandler.getDbInstance().getReference().child("users").get()
        .addOnCompleteListener(task -> {
          if (!task.isSuccessful()) {
            Log.e("firebase", "Error getting data", task.getException());
          } else {
            List<String> userList = new ArrayList<>();
            HashMap value = (HashMap) task.getResult().getValue();

            for (Object key : value.keySet()) {
              userList.add(key.toString());
            }

            Log.i(TAG, String.format("Users being added to the Recycler View"));
            handler.post(() -> {
              adapter.setupList(userList);
              progressBar.setVisibility(View.INVISIBLE);
            });
          }
        });

  }

  public void registerUser(Handler handler, Context activityContext, String userName) {
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
              firebaseDbHandler.addUserToDb(userName);

              Log.i(TAG, String.format("User %s being logged in", userName));

              firebaseDbHandler.setCurrentUserName(userName);
              handler.post(() -> {
                Intent intent = new Intent(activityContext, MessagingActivity.class);
                activityContext.startActivity(intent);
              });
            } else {
              Log.i(TAG, String.format("User %s already exists", userName));
              handler.post(
                  () -> Toast.makeText(activityContext, "User already exists! ",
                      Toast.LENGTH_SHORT).show());
            }
          }
        });
  }

  public void loginUser(Handler handler, Context activityContext, String userName) {
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

              firebaseDbHandler.setCurrentUserName(userName);
              handler.post(() -> {
                Intent intent = new Intent(activityContext, MessagingActivity.class);
                activityContext.startActivity(intent);
              });
            } else {
              Log.i(TAG, String.format("User %s does not exist", userName));
              handler.post(() -> Toast.makeText(activityContext,
                  "User does no exist, please register. ", Toast.LENGTH_SHORT).show());
            }
          }
        });
  }
}
