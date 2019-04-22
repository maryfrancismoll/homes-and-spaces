package com.model;

/**
 * @author Maryfrancis Remo Moll
 *
 * Model holds information used in displaying messages
 */
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
