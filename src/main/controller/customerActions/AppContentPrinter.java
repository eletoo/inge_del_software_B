package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.UserSelectable;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class AppContentPrinter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {

        if (this.getHierarchies(controller.getApp()).isEmpty()) {
            controller.signalToView(ErrorMessage.NO_HIERARCHIES_YET.getMessage());
            return;
        }

        controller.signalToView("GERARCHIE:");

        controller.signalListToView(
                new LinkedList<>(this.getHierarchies(controller.getApp()).values()),
                (Hierarchy h) -> h.getRoot().getCategoryDefinition()
        );

        if (controller.getApp().getInformationStore().getInformation() != null)
            controller.signalToView(controller.getApp().getInformationStore().getInformation().getInformations());
        else
            controller.signalToView(ErrorMessage.NO_INFO_YET.getMessage());
    }

    private Map<String, Hierarchy> getHierarchies(@NotNull Application app) {
        return app.getHierarchiesStore().getHierarchies();
    }

    @Override
    public String getActionName() {
        return "Visualizza il contenuto dell'applicazione e le informazioni di scambio";
    }

}
