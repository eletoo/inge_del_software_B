package main.controller;

public class DayMessageForView implements Message {
    private String message;

    public DayMessageForView(String dayName) {
        this.message = dayName;
    }

    public String getDayName() {
        return message;
    }
}
