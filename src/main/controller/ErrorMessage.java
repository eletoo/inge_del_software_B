package main.controller;

import org.jetbrains.annotations.Contract;

public enum ErrorMessage implements Message{
    E_CREDENTIALS_ERROR("Errore nell'inserimento delle credenziali"),
    E_UNREGISTERED_USER("Utente non registrato: username inesistente"),
    E_WRONG_PASSWORD("Password errata"),
    E_EXISTING_ROOT_CATEGORY("ERRORE: Il nome è gia' stato assegnato a un'altra categoria radice"),
    E_EXISTING_NAME_IN_HIERARCHY("ERRORE: Nome gia' presente nella gerarchia"),
    E_INVALID_TIME("ERRORE: uno degli orari inseriti non e' valido"),
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
    @Override
    public String getMessage() {
        return this.message;
    }

}
