package edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView.MessageCard;
import edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.Entities.User;

public class FirebaseDBHandler {

  private static final String TAG = "_FirebaseDBHandler";
  private static FirebaseDatabase dbInstance;

  private static String currentUserName = null;

  private static String partnerUserName = null;

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

  public void addUsersChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("users").addChildEventListener(childEventListener);
  }

  public void removeUsersChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("users").removeEventListener(childEventListener);
  }

  public void addUserChatChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("messages").child(currentUserName)
        .addChildEventListener(childEventListener);


  }

  public void removeUserChatChildEventListener(ChildEventListener childEventListener) {
    this.getDbInstance().getReference().child("messages").child(currentUserName)
        .removeEventListener(childEventListener);
  }

  public FirebaseDatabase getDbInstance() {
    return dbInstance;
  }

  public void addUserToDb(String uname) {
    User user = new User(uname);

    // add user to firebase database
    dbInstance.getReference().child("users").child(user.uname).setValue(user);
  }

  public void addMessageToDb(String partnerId, MessageCard messageCard) {

    setPartnerUserName(partnerId);

    DatabaseReference myRef = dbInstance.getReference("messages").child(currentUserName).child(partnerId);

    // Generate a new unique key for the message
    String messageId = dbInstance.getReference().child("messages").push().getKey();

    // setting messageCard for the sender of the message
    HashMap<String, Object> messageData = new HashMap<>();
    messageData.put("sent", messageCard.isSentByUser());
    messageData.put("timestamp", messageCard.getTimestamp());
    messageData.put("stickerId", messageCard.getStickerId());

    myRef.child(messageId).setValue(messageData);


  }

  public void setPartnerUserName(String partnerId) {
    partnerUserName = partnerId;
  }

}
