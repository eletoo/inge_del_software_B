package main.controller;

public class CustomMessage implements PrintableMessage {

    private String message;

    public CustomMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return this.message;
    }
}
