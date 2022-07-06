package main.controller.configuratorActions;

import main.Application;
import main.controller.Controller;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.Hierarchy;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyContentPrinter implements Selectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {

        for (Hierarchy h : app.getHierarchiesStore().getHierarchies().values()) {
            h.printHierarchy();
            h.getRoot().printCategory();
        }
    }

    @Override
    public String getActionName() {
        return "Visualizza il contenuto delle gerarchie attualmente presenti nel sistema";
    }

}
