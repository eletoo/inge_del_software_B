package main.controller.configuratorActions;

import main.model.OfferState;

public class ExchangingOffersPrinter extends AbstractOffersPrinter {

    public ExchangingOffersPrinter() {
        super(OfferState.IN_SCAMBIO);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte in scambio";
    }

}
