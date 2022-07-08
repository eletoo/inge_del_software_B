package main.controller.configuratorActions;

import main.controller.GenericMessage;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * configura le gerarchie da file
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class HierarchyFromFileConfigurator implements UserSelectable {
    /**
     * configura le gerarchie da file
     * @param controller controller
     * @param user utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        try {
            controller.getApp().getHierarchiesStore().loadFromFile();
            controller.signalToView(GenericMessage.SUCCESSFUL_HIERARCHY_IMPORT.getMessage());
        } catch (IOException e){
            controller.signalToView(e.getMessage());
        }
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Importa gerarchie da file";
    }
}
