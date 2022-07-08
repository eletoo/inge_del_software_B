package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;

/**
 * Gestisce l'uscita dall'applicazione
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class Exit implements Selectable {
    private String actionName;

    public Exit() {
        this.actionName = "Esci";
    }

    /**
     * esce dall'applicazione
     *
     * @param controller controller
     */
    @Override
    public void runAction(Controller controller) {
        System.exit(0);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return this.actionName;
    }
}
