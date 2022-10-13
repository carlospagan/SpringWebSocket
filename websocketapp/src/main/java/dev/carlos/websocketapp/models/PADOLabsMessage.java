package dev.carlos.websocketapp.models;

public class PADOLabsMessage {
    private String user;
    private String message;

    public PADOLabsMessage() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"" + "user\":\"" + user + '\"' + ", \"message\":\"" + message + '\"' + '}';
    }
}
