package main.controller.configuratorActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyContentPrinter implements UserSelectable {
    /**
     * Stampa il nome e il contenuto delle gerarchie
     * @param controller
     * @param user
     * @throws IOException
     */
    @Override
    public void runAction(Controller controller, User user) throws IOException {

        for (Hierarchy h : controller.getApp().getHierarchiesStore().getHierarchies().values()) {
            controller.signalToView(h.getRoot().getNome() + " " + h.getRoot().getDescrizione()); //todo
            controller.signalToView(h.getRoot().getCategoryDefinition()); //todo no string to view
        }
    }

    @Override
    public String getActionName() {
        return "Visualizza il contenuto delle gerarchie attualmente presenti nel sistema";
    }

}
