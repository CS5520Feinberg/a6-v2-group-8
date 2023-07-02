package edu.northeastern.NUMAD_23Su_Group8.Persistence.Firebase.Entities;

public class Message {

  public String senderUserName;
  public String receiverUserName;
  public int stickerId;

  public Message(String senderUserName, String receiverUserName, int stickerId) {
    if (stickerId < 1 || stickerId > 8) {
      throw new IllegalArgumentException("invalid stickerId");
    }

    this.receiverUserName = receiverUserName;
    this.senderUserName = senderUserName;
    this.stickerId = stickerId;
  }
}
