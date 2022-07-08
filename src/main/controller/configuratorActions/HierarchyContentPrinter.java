package main.controller.configuratorActions;

import main.controller.*;
import main.model.Category;
import main.model.Hierarchy;
import main.model.Node;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * stampa contenuto delle gerarchie
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class HierarchyContentPrinter implements UserSelectable {
    /**
     * Stampa il nome e il contenuto delle gerarchie
     *
     * @param controller
     * @param user
     * @throws IOException
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {

        for (Hierarchy h : controller.getApp().getHierarchiesStore().getHierarchies().values()) {
            controller.getView().printCategoryShortDescription(new CategoryShortMessageForView(h.getRoot().getNome() + " " + h.getRoot().getDescrizione()));

            if (h.getRoot() instanceof Node)
                controller.getView().printNodeDescription((NodeMessageForView) h.getRoot().getCategoryDefinition());
            else
                controller.getView().printCategoryLongDescription((CategoryLongMessageForView) h.getRoot().getCategoryDefinition());

        }
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza il contenuto delle gerarchie attualmente presenti nel sistema";
    }

}
