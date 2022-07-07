package main.controller.configuratorActions;

import main.controller.GenericMessage;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyFromFileConfigurator implements UserSelectable {
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        try {
            controller.getApp().getHierarchiesStore().loadFromFile();
            controller.signalToView(GenericMessage.SUCCESSFUL_HIERARCHY_IMPORT.getMessage());
        } catch (IOException e){
            controller.signalToView(e.getMessage());
        }
    }

    @Override
    public String getActionName() {
        return "Importa gerarchie da file";
    }
}
