package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InfoFromFileConfigurator implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        //todo: implementare
        app.importInfoFromFile(this.view);
    }

    @Override
    public String getActionName() {
        return "Configura informazioni di scambio da file";
    }
}
