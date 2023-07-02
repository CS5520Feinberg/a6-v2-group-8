package edu.northeastern.NUMAD_23Su_Group8.Messaging.RecyclerView;

public class UserCard {
  private String userName;

  /**
   * Constructor for Card which will display the user's name.
   * @param userName the name of the user in the card.
   */
  public UserCard(String userName) {
    this.userName = userName;
  }


  public String getUserName() {
    return userName;
  }
}
