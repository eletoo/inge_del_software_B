package main.controller;

import org.jetbrains.annotations.Contract;

public enum YesOrNoMessage implements Message {
    ADD_ADDRESS("Inserire un altro luogo per lo scambio? [Y/N]"),
    ADD_DAY("Inserire un altro giorno per lo scambio? [Y/N]"),
    ADD_TIMERANGE("Inserire un altra fascia oraria per lo scambio? [Y/N]"),
    OVERWRITE_INFO("\nSovrascrivere le informazioni di scambio presenti (N.B. La piazza non è modificabile)? [Y/N]"),
    ADD_NATIVE_FIELD("Inserire un altro campo descrittivo alla categoria? (Y/N)"),
    COMPULSORY_FIELD("Campo a compilazione obbligatoria? (Y/N)"),
    ADD_CATEGORY("Aggiungere una nuova categoria? [Y/N]"),
    SAVE_HIERARCHY("Salvare la gerarchia creata? [Y/N]");

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
