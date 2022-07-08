package main.controller.customerActions;

import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.Offer;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * stampa le offerte dell'utente
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class PersonalOffersPrinter implements UserSelectable {
    /**
     * stampa le offerte dell'utente
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        controller.signalListToView(
                controller.getApp().getOffersStore().getOffers(user),
                Offer::getOfferInfos
        );
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza offerte personali";
    }
}
