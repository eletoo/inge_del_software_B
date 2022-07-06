package main.controller.configuratorActions;

import main.Application;
import main.controller.Controller;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyFromFileConfigurator implements Selectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {
        app.getHierarchiesStore().loadFromFile();
    }

    @Override
    public String getActionName() {
        return "Importa gerarchie da file";
    }
}
