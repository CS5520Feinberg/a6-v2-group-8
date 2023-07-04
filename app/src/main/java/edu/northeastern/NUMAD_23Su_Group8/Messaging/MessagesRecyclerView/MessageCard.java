package edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView;

public class MessageCard {

    private String stickerId;
    private String timestamp;
    private boolean sentByUser;

    /**
     * Constructor for Card which will display the message in a user "conversation".
     * @param stickerId -- the sticker that was sent
     * @param timestamp -- the time the sticker was sent
     * @param sentByUser -- TRUE if the user sent the sticker, FALSE if the sticker was
     *                   received from another user.
     */
    public MessageCard(String stickerId, String timestamp, boolean sentByUser) {
        this.stickerId = stickerId;
        this.timestamp = timestamp;
        this.sentByUser = sentByUser;
    }

    public String getStickerId() {
        return stickerId;
    }

    public void setStickerId(String stickerId) {
        this.stickerId = stickerId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }
}
