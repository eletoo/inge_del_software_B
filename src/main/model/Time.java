package main.model;

import main.controller.CustomMessage;
import main.controller.Message;
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
    public Message getTime() {
        String hour = this.hour == 0 ? "00" : String.valueOf(this.hour);
        String mins = this.minutes == 0 ? "00" : String.valueOf(this.minutes);
        return new CustomMessage(hour + ":" + mins);
    }

    /**
     * Verifica la validita' di un orario (i.e. se l'ora e' compresa tra 0 e 23 e se i minuti sono 00 o 30 oppure se sono le 24:00)
     *
     * @param h ora
     * @param m minuti
     * @return true se l'orario e' nel formato richiesto
     */
    public boolean isValid(int h, int m) {
        if (h >= 0 && h <= 23)
            if (m == 0 || m == 30)
                return true;
        if (h == 24 && m == 0)
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
