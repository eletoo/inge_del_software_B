package main.controller.customerActions;

import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.Application;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

/**
 * stampa il contenuto dell'applicazione
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class AppContentPrinter implements UserSelectable {
    /**
     * stampa il contenuto delle gerarchie e le informazioni di scambio
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {

        if (this.getHierarchies(controller.getApp()).isEmpty()) {
            controller.signalToView(ErrorMessage.NO_HIERARCHIES_YET);
            return;
        }

        controller.signalToView(GenericMessage.HIERARCHIES);

        controller.signalListToView(
                new LinkedList<>(this.getHierarchies(controller.getApp()).values()),
                (Hierarchy h) -> h.getRoot().getCategoryDefinition()
        );

        if (controller.getApp().getInformationStore().getInformation() != null)
            controller.getView().printInfo(controller.getApp().getInformationStore().getInformation().getInformations());
        else
            controller.signalToView(ErrorMessage.NO_INFO_YET);
    }

    /**
     * @param app applicazione
     * @return insieme delle gerarchie
     */
    private Map<String, Hierarchy> getHierarchies(@NotNull Application app) {
        return app.getHierarchiesStore().getHierarchies();
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza il contenuto dell'applicazione e le informazioni di scambio";
    }

}
