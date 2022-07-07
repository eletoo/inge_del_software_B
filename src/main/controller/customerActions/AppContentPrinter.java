package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.UserSelectable;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public class AppContentPrinter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {

        if (this.getHierarchies(controller.getApp()).isEmpty()) {
            controller.signalToView(ErrorMessage.NO_HIERARCHIES_YET.getMessage());
            return;
        }
        controller.signalToView("GERARCHIE:");

        for (Hierarchy h : this.getHierarchies(controller.getApp()).values()) {
            //h.printHierarchy();
        }

        if (controller.getApp().getInformationStore().getInformation() != null)
            controller.getApp().getInformationStore().getInformation().print();
        else
            Controller.signalToView(ErrorMessage.NO_INFO_YET.getMessage());
    }

    private Map<String, Hierarchy> getHierarchies(@NotNull Application app) {
        return app.getHierarchiesStore().getHierarchies();
    }

    @Override
    public String getActionName() {
        return "Visualizza il contenuto dell'applicazione e le informazioni di scambio";
    }

}
