package main.model;

import java.io.Serializable;

public class ExchangeMessage implements Serializable {

    private String message;
    private Customer author;

    /**
     * Costruttore
     */
    public ExchangeMessage(String message, Customer fruitore) {
        this.message = message;
        this.author = fruitore;
    }

    /**
     * @return messaggio
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return autore del messaggio
     */
    public Customer getAuthor() {
        return this.author;
    }

    /**
     * @param message messaggio da impostare
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param f autore da impostare
     */
    public void setAuthor(Customer f) {
        this.author = f;
    }
}
