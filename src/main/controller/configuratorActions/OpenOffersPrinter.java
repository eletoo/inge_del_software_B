package main.controller.configuratorActions;

import main.model.OfferState;

public class OpenOffersPrinter extends AbstractOffersPrinter {
    public OpenOffersPrinter() {
        super(OfferState.APERTA);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte aperte per categoria";
    }
}
