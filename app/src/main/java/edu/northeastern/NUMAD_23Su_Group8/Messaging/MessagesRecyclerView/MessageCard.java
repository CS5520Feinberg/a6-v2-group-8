package edu.northeastern.NUMAD_23Su_Group8.Messaging.MessagesRecyclerView;

public class MessageCard {

    private int stickerResourceId;
    private long timestamp;
    private boolean sentByUser;

    /**
     * Constructor for Card which will display the message in a user "conversation".
     * @param stickerId -- the sticker that was sent
     * @param timestamp -- the time the sticker was sent
     * @param sentByUser -- TRUE if the user sent the sticker, FALSE if the sticker was
     *                   received from another user.
     */
    public MessageCard(int stickerId, long timestamp, boolean sentByUser) {
        this.stickerResourceId = stickerId;
        this.timestamp = timestamp;
        this.sentByUser = sentByUser;
    }

    public MessageCard(){}

    public int getStickerId() {
        return stickerResourceId;
    }

    public void setStickerId(int stickerId) {
        this.stickerResourceId = (int) stickerId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }
}
