package main.controller.customerActions;

import main.Application;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.Selectable;
import main.controller.UserSelectable;
import main.exceptions.InvalidMethodException;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

public class AppContentPrinter implements UserSelectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller, User user) throws IOException {

        if (this.getHierarchies(app).isEmpty()) {
            Controller.signalToView(ErrorMessage.NO_HIERARCHIES_YET.getMessage());
            return;
        }
        Controller.signalToView("GERARCHIE:");

        for (Hierarchy h : this.getHierarchies(app).values()) {
            h.printHierarchy();
        }

        if (app.getInformationStore().getInformation() != null)
            app.getInformationStore().getInformation().print();
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
