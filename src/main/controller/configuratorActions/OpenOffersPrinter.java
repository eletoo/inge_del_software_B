package main.controller.configuratorActions;

import main.model.OfferState;

/**
 * stampa le offerte aperte
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class OpenOffersPrinter extends AbstractOffersPrinter {
    public OpenOffersPrinter() {
        super(OfferState.APERTA);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza offerte aperte per categoria";
    }
}
