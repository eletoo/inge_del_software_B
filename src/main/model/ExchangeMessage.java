package main.model;

import main.controller.ExchangeMessageForView;

import java.io.Serializable;

public class ExchangeMessage implements Serializable {

    private ExchangeMessageForView message;
    private Customer author;

    /**
     * Costruttore
     */
    public ExchangeMessage(ExchangeMessageForView message, Customer fruitore) {
        this.message = message;
        this.author = fruitore;
    }

    /**
     * @return messaggio
     */
    public ExchangeMessageForView getMessage() {
        return this.message;
    }

    /**
     * @param message messaggio da impostare
     */
    public void setMessage(ExchangeMessageForView message) {
        this.message = message;
    }

    /**
     * @return autore del messaggio
     */
    public Customer getAuthor() {
        return this.author;
    }

    /**
     * @param f autore da impostare
     */
    public void setAuthor(Customer f) {
        this.author = f;
    }
}
