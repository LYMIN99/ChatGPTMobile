package com.coddy.chatgptmobile.model;

public class ChoiceModel {
    private MessageModel message;
    private String finishReason;
    private long index;

    public MessageModel getMessage() { return message; }
    public void setMessage(MessageModel value) { this.message = value; }

    public String getFinishReason() { return finishReason; }
    public void setFinishReason(String value) { this.finishReason = value; }

    public long getIndex() { return index; }
    public void setIndex(long value) { this.index = value; }
}
