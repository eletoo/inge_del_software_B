package main.view;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum ErrorMessage {
    E_EMPTY_USER_DB("Non c'è alcun utente registrato -- crea un primo profilo Configuratore"),
    E_CREDENTIALS_ERROR("Errore nell'inserimento delle credenziali"),
    E_DIFFERENT_PASSWORDS("Le password non coincidono"),
    E_USERNAME_TAKEN("Username già in uso da un altro utente"),
    E_UNREGISTERED_USER("Utente non registrato: username inesistente"),
    E_WRONG_PASSWORD("Password errata"),
    E_ILLICIT_CHOICE("Opzione non consentita"),
    E_EXISTING_ROOT_CATEGORY("ERRORE: Il nome è già stato assegnato a un'altra categoria radice"),
    E_EXISTING_NAME_IN_HIERARCHY("ERRORE: Nome già presente nella gerarchia"),
    E_UNAUTHORIZED_CHOICE("ERRORE: Azione non consentita"),
    E_WRONG_FORMAT("ERRORE: formato errato, inserire un numero"),
    E_INVALID_TIME("ERRORE: uno degli orari inseriti non è valido"),
    E_INVALID_TIME_RANGE("ERRORE: intervallo orario invalido"),
    E_INVALID_INPUT("ERRORE: Input invalido"),
    E_NO_CATEGORIES("Non ci sono categorie selezionabili"),
    E_NO_OFFERS("Non ci sono offerte selezionabili"),
    E_NO_INFO("Non sono ancora state impostate le informazioni di scambio -- Contattare un amministratore"),
    NO_INFO_YET("Non sono ancora presenti informazioni relative agli scambi"),
    NO_HIERARCHIES_YET("Non sono ancora presenti gerarchie all'interno dell'applicazione"),
    E_INVALID_FILE_CONTENT("File di configurazione inesistente o corrotto"),
    E_WRONG_DIR_CONTENT("E' stato trovato più di un file di configurazione -- Numero file consentiti: 1"),
    E_NO_CONF_FILE("Impossibile configurare le impostazioni di scambio da file -- configurare manualmente");

    private String message;

    @Contract(pure = true)
    ErrorMessage(String message) {
        this.message = message;
    }

    @Contract(pure = true)
    public String getMessage() {
        return this.message;
    }

    /**
     * Presenta all'utente un messaggio di errore
     *
     * @param err messaggio di errore
     */
    public static void printErrorMessage(@NotNull ErrorMessage err) {
        System.err.println(err.getMessage());
    }

}
