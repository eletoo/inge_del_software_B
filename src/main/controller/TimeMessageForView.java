package main.controller;

public class TimeMessageForView implements Message {

    private int hour;
    private int min;

    public TimeMessageForView(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }
}
