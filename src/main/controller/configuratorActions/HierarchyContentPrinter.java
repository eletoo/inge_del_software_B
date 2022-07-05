package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyContentPrinter implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        //todo: implementare
        for (String r : app.getHierarchiesStore().getHierarchies().keySet()) {
            System.out.println(app.getHierarchy(r).toString());
            System.out.println(app.getHierarchy(r).getRoot().toString());
        }
    }

    @Override
    public String getActionName() {
        return "Visualizza il contenuto delle gerarchie attualmente presenti nel sistema";
    }
}
