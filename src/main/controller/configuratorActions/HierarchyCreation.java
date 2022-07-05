package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class HierarchyCreation implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        createNewHierarchy();
    }

    @Override
    public String getActionName() {
        return "Crea una nuova gerarchia";
    }

    private void createNewHierarchy(){
        //todo: implementare
    }
}
