package main.controller.configuratorActions;

import main.model.OfferState;

/**
 * stampa le offerte in stato in sambio
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class ExchangingOffersPrinter extends AbstractOffersPrinter {

    public ExchangingOffersPrinter() {
        super(OfferState.IN_SCAMBIO);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza offerte in scambio";
    }

}
