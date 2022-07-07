package main.model;

import java.io.Serializable;

public enum Day implements Serializable {
    MONDAY("Lunedi'"),
    TUESDAY("Martedi'"),
    WEDNESDAY("Mercoledi'"),
    THURSDAY("Giovedi'"),
    FRIDAY("Venerdi'"),
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
