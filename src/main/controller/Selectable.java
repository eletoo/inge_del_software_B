package main.controller;

import java.io.IOException;

/**
 * interfaccia da implementare per rendere un'azione selezionabile ed eseguibile
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public interface Selectable {

    void runAction(Controller controller) throws IOException;

    String getActionName();
}
