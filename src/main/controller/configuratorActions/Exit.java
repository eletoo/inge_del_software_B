package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * esce dal menù utente
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class Exit implements UserSelectable {
    /**
     * esce dal menù utente
     * @param controller controller
     * @param user utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        controller.signalToView(GenericMessage.EXIT_MESSAGE);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Logout";
    }

}
