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

    private String dayName;

    Day(String dayName) {
        this.dayName = dayName;
    }

    public String getDay() {
        return this.dayName;
    }
}
