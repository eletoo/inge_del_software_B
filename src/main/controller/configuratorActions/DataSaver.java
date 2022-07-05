package main.controller.configuratorActions;

import main.Application;
import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DataSaver implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        app.getHierarchiesStore().save();
        app.getInformationStore().save();
        app.getOffersStore().save();
        app.getExchangesStore().save();
        Controller.signalToView(GenericMessage.SAVED_CORRECTLY.getMessage());
    }

    @Override
    public String getActionName() {
        return "Salva";
    }
}
