package main.controller.configuratorActions;

import main.model.OfferState;

/**
 * Stampa le offerte in stato chiuso
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class ClosedOffersPrinter extends AbstractOffersPrinter {
    public ClosedOffersPrinter() {
        super(OfferState.CHIUSA);
    }

    /**
     * @return descizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza offerte chiuse";
    }
}
