package main.controller;

import org.jetbrains.annotations.Contract;

public enum GenericMessage implements Message {
    SELECT_INDEX("\nSelezionare un indice."),
    USERNAME_REQUEST("Inserisci il tuo username: "),
    PASSWORD_REQUEST("Inserisci la tua password: "),
    STD_CREDENTIALS_MSG("\nPuoi accedere al tuo profilo usando le seguenti credenziali:"),
    CUSTOMIZE_CREDENTIALS("Personalizza le tue credenziali: "),
    CUSTOMIZE_USERNAME("Inserisci il tuo username personalizzato: "),
    CUSTOMIZE_PW("Inserisci la tua password personalizzata: "),
    SELECT_PROFILE_TYPE("Seleziona la modalità con cui registrarti: "),
    SUCCESSFUL_IMPORT("Informazioni importate correttamente"),
    SUCCESSFUL_HIERARCHY_IMPORT("Gerarchie importate correttamente"),
    EXIT_MESSAGE("Arrivederci"),
    SAVED_CORRECTLY("Salvataggio eseguito correttamente"),
    CURRENT_INFO("\nAttualmente sono presenti le seguenti informazioni di scambio: "),
    STARTING_HOUR("Ora di inizio [hh] [00-24]: "),
    STARTING_MINUTES("Minuti di inizio [mm] [00 o 30]: "),
    ENDING_HOUR("Ora di fine [hh] [00-24]: "),
    ENDING_MINUTES("Minuti di fine [mm] [00 o 30]: "),
    PLACE("Piazza (N.B. NON modificabile in futuro): "),
    ADDRESS("Luogo: "),
    DAY("Giorno: "),
    EXCHANGE_HOURS_EVERY_30_MINS("\nGli scambi potranno avvenire allo scoccare dell'ora o della mezz'ora all'interno della fascia oraria specificata"),
    DEADLINE("Scadenza dell'offerta dopo un numero di giorni pari a (inserire un numero maggiore di 0): "),
    CATEGORY_NAME("Inserisci il nome della categoria: "),
    CATEGORY_DESCRIPTION("Inserire la descrizione della categoria: "),
    FIELD_NAME("Nome campo: "),
    AT_LEAST_TWO_CHILDREN("\nN.B. Ogni categoria nodo deve avere almeno due sotto-categorie"),
    SELECT_CATEGORY("Scegliere una categoria della quale visualizzare le offerte: "),
    NAME("Nome: "),
    CHOOSE_CATEGORY_TO_PUBLISH("Selezionare una categoria dove pubblicare l'offerta");

    private String message;

    @Contract(pure = true)
    GenericMessage(String message) {
        this.message = message;
    }

    @Contract(pure = true)
    @Override
    public String getMessage() {
        return this.message;
    }

}
