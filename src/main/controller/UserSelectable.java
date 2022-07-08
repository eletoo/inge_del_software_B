package main.controller;

import main.model.User;

import java.io.IOException;

/**
 * interfaccia per rendere un'azione selezionabile da un utente nel menu personale
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public interface UserSelectable {

    void runAction(Controller controller, User user) throws IOException;

    String getActionName();
}
