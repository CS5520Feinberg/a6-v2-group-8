package edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.Entities.User;

public class FirebaseDBHandler {

  private static final String TAG = "_FirebaseDBHandler";
  private static FirebaseDatabase dbInstance;

  private static String currentUserName = null;
  private static FirebaseDBHandler INSTANCE;

  private static void initDbReference() {
    if (dbInstance == null) {
      dbInstance = FirebaseDatabase.getInstance();
    }
  }

  private FirebaseDBHandler() {
    initDbReference();
  }

  public static FirebaseDBHandler getInstance() {
    if(INSTANCE == null) {
      INSTANCE = new FirebaseDBHandler();
    }

    return INSTANCE;
  }

  public void setCurrentUserName(String userName) {
    currentUserName = userName;
  }

  public String getCurrentUserName() {
    return currentUserName;
  }

  // TODO: use when resuming MessagingActivity
  public void addUsersChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("users").addChildEventListener(childEventListener);
  }

  // TODO: use when pausing MessagingActivity
  public void removeUsersChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("users").removeEventListener(childEventListener);
  }

  // TODO: use when creating MessagingChatActivity
  public void addUserChatChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("messages").child(currentUserName)
        .addChildEventListener(childEventListener);
  }

  // TODO: use when destroying MessagingChatActivity
  public void removeUserChatChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("messages").child(currentUserName)
        .removeEventListener(childEventListener);
  }

  public FirebaseDatabase getDbInstance() {
    return dbInstance;
  }

  public void addUserToDb(String uname) {
    DatabaseReference myRef = dbInstance.getReference("message");
    User user = new User(uname);

    // add user to firebase database
    dbInstance.getReference().child("users").child(user.uname).setValue(user);

    // NOTE: Not needed
//    // Read from the database by listening for a change to that item.
//    myRef.addValueEventListener(new ValueEventListener() {
//      @Override
//      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//        String value = dataSnapshot.getValue(String.class);
//        Log.d(TAG, "Value is: " + value);
//      }
//
//      @Override
//      public void onCancelled(@NonNull DatabaseError error) {
//        Log.w(TAG, "Failed to read value.", error.toException());
//      }
//    });
  }
}
