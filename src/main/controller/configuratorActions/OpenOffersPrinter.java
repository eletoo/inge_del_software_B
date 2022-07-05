package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import main.model.Offer;

import java.io.IOException;

public class OpenOffersPrinter implements Selectable {
    @Override
    public void runAction(Application app) throws IOException {
        //todo: implementare
        Offer.viewOffersByCategory(app, view);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte";
    }
}
