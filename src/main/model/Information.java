package main.model;

import main.controller.Controller;

import java.io.Serializable;
import java.util.List;

public class Information implements Serializable {
    private String place;
    private List<String> addresses;
    private List<TimeRange> timeIntervals;
    private int deadline;
    private List<Day> days;

    public Information(String place, List<String> address, List<Day> days, List<TimeRange> timeIntervals, int deadline){
        this.place = place;
        this.addresses = address;
        this.days = days;
        this.timeIntervals = timeIntervals;
        this.deadline = deadline;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public String getPlace() {
        return place;
    }

    public List<TimeRange> getTimeIntervals() {
        return timeIntervals;
    }

    public int getDeadline() {
        return deadline;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public void setTimeIntervals(List<TimeRange> timeIntervals) {
        this.timeIntervals = timeIntervals;
    }

    public String getInformations(){
        StringBuilder sb = new StringBuilder();
        sb.append("INFORMAZIONI SCAMBI:");
        sb.append("\nPiazza: ");
        sb.append(this.place);
        sb.append("\nLuoghi: ");
        for (int i = 0; i < this.addresses.size(); i++) {
            sb.append("\n -> ");
            sb.append(this.addresses.get(i));
        }
        sb.append("\nGiorni: ");
        for (int i = 0; i < this.days.size(); i++) {
            sb.append(this.days.get(i).getDay());
            if (i < this.days.size() - 1)
                sb.append(", ");
        }
        sb.append("\nIntervalli Orari: ");
        for (int i = 0; i < this.timeIntervals.size(); i++) {
            sb.append(this.timeIntervals.get(i).printTimeRange());
            if (i < this.timeIntervals.size() - 1)
                sb.append(", ");
        }
        sb.append("\nScadenza offerte dopo " + this.deadline + " giorni");
        return sb.toString();
    }
}
