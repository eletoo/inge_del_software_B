package main.model;

import main.controller.Message;
import main.controller.TimeMessageForView;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Time implements Serializable {
    private int hour;
    private int minutes;

    /**
     * Costruttore
     *
     * @param hour    ora
     * @param minutes minuti
     */
    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    /**
     * @return stringa contenente la descrizione dell'orario nel formato hh:mm
     */
    public TimeMessageForView getTimeDescription() {
        return new TimeMessageForView(this.hour, this.minutes);
    }

    /**
     * Verifica la validita' di un orario (i.e. se l'ora e' compresa tra 0 e 23 e se i minuti sono 00 o 30 oppure se sono le 24:00)
     *
     * @return true se l'orario e' nel formato richiesto
     */
    public boolean isValid() {
        if (this.hour >= 0 && this.hour <= 23)
            if (this.minutes == 0 || this.minutes == 30)
                return true;
        if (this.hour == 24 && this.minutes == 0)
            return true;
        return false;
    }

    /**
     * @return minuti
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @return ora
     */
    public int getHour() {
        return hour;
    }

    /**
     * Imposta l'ora di un orario
     *
     * @param hour ora
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Imposta i minuti di un orario
     *
     * @param minutes minuti
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Verifica se l'orario e' successivo a quello passato come parametro. Se gli orari sono uguali ritorna true.
     *
     * @param o orario da confrontare
     * @return true se l'orario su cui e' chiamato il metodo e' uguale o successivo all'orario passato come parametro
     */
    public boolean isLaterThan(@NotNull Time o) {
        if (this.getHour() > o.getHour())
            return true;
        if (this.getHour() == o.getHour() && this.getMinutes() >= o.getMinutes())
            return true;
        return false;
    }

    /**
     * Verifica se due orari sono lo stesso orario
     *
     * @param o orario da confrontare
     * @return true se gli orari sono gli stessi
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time orario = (Time) o;
        return hour == orario.hour && minutes == orario.minutes;
    }

    /**
     * Override del metodo hashCode() della classe Object
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(hour, minutes);
    }
}
