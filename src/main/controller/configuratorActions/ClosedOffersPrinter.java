package main.controller.configuratorActions;

import main.Application;
import main.controller.Selectable;
import main.model.Offer;

import java.io.IOException;

public class ClosedOffersPrinter implements Selectable {
    @Override
    public void runAction(Application app) throws IOException {
        //todo: implementare
        Offer.viewOffers(app, view, Offer.StatoOfferta.CHIUSA);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte chiuse";
    }
}
