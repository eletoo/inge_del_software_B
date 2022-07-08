package main.controller.customerActions;

/**
 * stampa le offerte aperte
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class OpenOffersPrinter extends main.controller.configuratorActions.OpenOffersPrinter {
    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza offerte aperte per categoria";
    }
}
