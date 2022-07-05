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

    public Information(String place, List<String> address, List<TimeRange> timeIntervals, int deadline, List<Day> days){
        this.place = place;
        this.addresses = address;
        this.timeIntervals = timeIntervals;
        this.deadline = deadline;
        this.days = days;
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

    public void print(){
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

        Controller.signalToView(sb.toString());
    }
}
