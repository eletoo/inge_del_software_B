package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import main.model.OfferState;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ClosedOffersPrinter implements Selectable {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        app.getOffersStore().viewOffers(app, OfferState.CHIUSA);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte chiuse";
    }
}
