package main.controller;

import main.model.Day;
import main.model.Time;
public class ExchangeMessageForView implements Message {

    private String place;
    private String address;
    private Day day;
    private Time timeRange;

    public ExchangeMessageForView(String place, String addresses, Day days, Time timeRanges) {
        this.place = place;
        this.address = addresses;
        this.day = days;
        this.timeRange = timeRanges;
    }

    public String getPlace() {
        return place;
    }

    public String getAddress() {
        return address;
    }

    public Day getDay() {
        return day;
    }

    public Time getTimeRange() {
        return timeRange;
    }
}
