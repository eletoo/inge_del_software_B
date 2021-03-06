package main.controller;

import org.jetbrains.annotations.Contract;

/**
 * messaggi che richiedono una risposta si/no dall'utente
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public enum YesOrNoMessage implements PrintableMessage {
    ADD_ADDRESS("Inserire un altro luogo per lo scambio? [Y/N]"),
    ADD_DAY("Inserire un altro giorno per lo scambio? [Y/N]"),
    ADD_TIMERANGE("Inserire un altra fascia oraria per lo scambio? [Y/N]"),
    OVERWRITE_INFO("\nSovrascrivere le informazioni di scambio presenti (N.B. La piazza non e' modificabile)? [Y/N]"),
    ADD_NATIVE_FIELD("Inserire un altro campo descrittivo alla categoria? (Y/N)"),
    COMPULSORY_FIELD("Campo a compilazione obbligatoria? (Y/N)"),
    ADD_CATEGORY("Aggiungere una nuova categoria? [Y/N]"),
    SAVE_HIERARCHY("Salvare la gerarchia creata? [Y/N]"),
    SELECT_EXCHANGE("\nSelezionare uno scambio da gestire dalla lista? [Y/N]"),
    ACCEPT_MEETING("\nAccettare l'appuntamento? [Y/N]");

    private String message;

    @Contract(pure = true)
    YesOrNoMessage(String message) {
        this.message = message;
    }

    @Contract(pure = true)
    @Override
    public String getMessage() {
        return this.message;
    }
}
