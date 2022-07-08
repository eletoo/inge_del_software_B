package main.controller;

import main.model.Day;
import main.model.TimeRange;

import java.util.List;

public class InformationMessageForView implements Message {

    private String place;
    private List<String> addresses;
    private List<Day> days;
    private List<TimeRange> time;

    private int deadline;

    public InformationMessageForView(String place, List<String> addresses, List<Day> days, List<TimeRange> time, int deadline) {
        this.place = place;
        this.addresses = addresses;
        this.days = days;
        this.time = time;
        this.deadline = deadline;
    }

    public int getDeadline() {
        return deadline;
    }

    public String getPlace() {
        return place;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public List<Day> getDays() {
        return days;
    }

    public List<TimeRange> getTime() {
        return time;
    }
}
