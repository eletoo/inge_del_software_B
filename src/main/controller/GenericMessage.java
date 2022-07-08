package main.controller;

import org.jetbrains.annotations.Contract;

/**
 * contiene generici messaggi da passare alla view
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public enum GenericMessage implements PrintableMessage {
    SELECT_INDEX("\nSelezionare un indice."),
    USERNAME_REQUEST("Inserisci il tuo username: "),
    PASSWORD_REQUEST("Inserisci la tua password: "),
    CUSTOMIZE_CREDENTIALS("Accesso effettuato con credenziali provvisorie -- Personalizza le tue credenziali: "),
    CUSTOMIZE_USERNAME("Inserisci il tuo username personalizzato: "),
    CUSTOMIZE_PW("Inserisci la tua password personalizzata: "),
    SELECT_PROFILE_TYPE("Seleziona la modalita' con cui registrarti: "),
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
    TIME_RANGE("Intervallo orario: "),
    EXCHANGE_HOURS_EVERY_30_MINS("\nGli scambi potranno avvenire allo scoccare dell'ora o della mezz'ora all'interno della fascia oraria specificata"),
    DEADLINE("Scadenza dell'offerta dopo un numero di giorni pari a (inserire un numero maggiore di 0): "),
    CATEGORY_NAME("Inserisci il nome della categoria: "),
    CATEGORY_DESCRIPTION("Inserire la descrizione della categoria:\n-- ENTER per saltare -- "),
    FIELD_NAME("Nome campo: "),
    AT_LEAST_TWO_CHILDREN("\nN.B. Ogni categoria nodo deve avere almeno due sotto-categorie"),
    SELECT_CATEGORY("Scegliere una categoria della quale visualizzare le offerte: "),
    NAME("Nome: "),
    CHOOSE_CATEGORY_TO_PUBLISH("Selezionare una categoria dove pubblicare l'offerta"),
    CHOOSE_OFFER("\nSeleziona una tua offerta da scambiare: "),
    CHOOSE_OTHER_OFFER("\nSeleziona un'offerta con cui scambiare il tuo prodotto: "),
    NO_PAST_OFFERS("\n\nNon hai nessun'altra proposta di scambio arretrata"),
    PAST_OFFERS("\nLe tue proposte di scambio arretrate:"),
    NO_NEW_OFFERS("\nNon hai nessuna nuova proposta di scambio"),
    NEW_OFFERS("\nLe tue nuove proposte di scambio:"),
    CLOSED_OFFER("\nOfferta chiusa\n"),
    OPTIONAL_FIELD("-- SOLO per campi opzionali: Enter per saltare --"),
    HIERARCHIES("GERARCHIE:"),
    SUGGEST_MEETING("--Proponi un appuntamento--");

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
