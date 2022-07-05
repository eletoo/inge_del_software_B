package main.model;

import java.io.Serializable;

public enum Day implements Serializable {
    MONDAY("Lunedì"),
    TUESDAY("Martedì"),
    WEDNESDAY("Mercoledì"),
    THURSDAY("Giovedì"),
    FRIDAY("Venerdì"),
    SATURDAY("Sabato"),
    SUNDAY("Domenica");

    Day(String dayName) {
        this.dayName = dayName;
    }

    private String dayName;

    public String getDay() {
        return this.dayName;
    }
}
