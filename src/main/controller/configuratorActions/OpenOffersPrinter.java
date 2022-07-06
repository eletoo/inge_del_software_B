package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpenOffersPrinter implements Selectable {

    @Override
    public void runAction(@NotNull Application app) throws IOException {
        app.getOffersStore().viewOffersByCategory(app);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte";
    }
}
