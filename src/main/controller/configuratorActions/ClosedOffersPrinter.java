package main.controller.configuratorActions;

import main.model.*;

public class ClosedOffersPrinter extends AbstractOffersPrinter {
    public ClosedOffersPrinter() {
        super(OfferState.CHIUSA);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte chiuse";
    }
}
