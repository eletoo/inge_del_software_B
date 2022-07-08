package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Salva i dati
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class DataSaver implements UserSelectable {
    /**
     * salva i dati
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        controller.getApp().save();
        controller.signalToView(GenericMessage.SAVED_CORRECTLY);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Salva";
    }

}
