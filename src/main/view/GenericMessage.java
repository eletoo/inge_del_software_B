package main.view;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum GenericMessage {
    SELECT_INDEX("\nSelezionare un indice."),
    USERNAME_REQUEST("Inserisci il tuo username: "),
    PASSWORD_REQUEST("Inserisci la tua password: "),
    STD_CREDENTIALS_MSG("\nPuoi accedere al tuo profilo usando le seguenti credenziali:"),
    CUSTOMIZE_CREDENTIALS("Personalizza le tue credenziali: "),
    CUSTOMIZE_USERNAME("Inserisci il tuo username personalizzato: "),
    CUSTOMIZE_PW("Inserisci la tua password personalizzata: "),
    SELECT_PROFILE_TYPE("Seleziona la modalità con cui registrarti: ");

    private String message;

    @Contract(pure = true)
    GenericMessage(String message) {
        this.message = message;
    }

    @Contract(pure = true)
    public String getMessage() {
        return this.message;
    }

    /**
     * Presenta all'utente un messaggio
     *
     * @param msg messaggio
     */
    public static void printGenericMessage(@NotNull GenericMessage msg) {
        System.out.println(msg.getMessage());
    }

}
