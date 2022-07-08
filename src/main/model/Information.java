package main.model;

import main.controller.InformationMessageForView;

import java.io.Serializable;
import java.util.List;

public class Information implements Serializable {
    private String place;
    private List<String> addresses;
    private List<TimeRange> timeIntervals;
    private int deadline;
    private List<Day> days;

    public Information(String place, List<String> address, List<Day> days, List<TimeRange> timeIntervals, int deadline) {
        this.place = place;
        this.addresses = address;
        this.days = days;
        this.timeIntervals = timeIntervals;
        this.deadline = deadline;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<TimeRange> getTimeIntervals() {
        return timeIntervals;
    }

    public void setTimeIntervals(List<TimeRange> timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public InformationMessageForView getInformations() {
        return new InformationMessageForView(this.place, this.addresses, this.days, this.timeIntervals, this.deadline);
    }


}
