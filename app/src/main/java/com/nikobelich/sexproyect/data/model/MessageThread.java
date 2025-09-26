package com.nikobelich.sexproyect.data.model;

public class MessageThread {

    private final Match match;
    private final String lastMessage;
    private final String timestamp;
    private final boolean unread;

    public MessageThread(Match match, String lastMessage, String timestamp, boolean unread) {
        this.match = match;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.unread = unread;
    }

    public Match getMatch() {
        return match;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public boolean isUnread() {
        return unread;
    }
}
