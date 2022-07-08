package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Gestisce la stampa delle offerte
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public abstract class AbstractOffersPrinter implements UserSelectable {
    private OfferState requiredState;

    public AbstractOffersPrinter(OfferState requiredState) {
        this.requiredState = requiredState;
    }

    /**
     * Stampa tutte le offerte di tutti gli utenti con stato selezionato
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        if (controller.getApp().getHierarchiesStore().getHierarchies().size() == 0) {
            controller.signalToView(ErrorMessage.E_NO_CATEGORIES);
            return;
        }

        Leaf leaf = controller.getView().choose(
                GenericMessage.SELECT_CATEGORY,
                controller.getApp().getOffersStore().getLeafCategories(controller.getApp()).stream().map(e -> (Leaf) e.getCat()).collect(Collectors.toList()),
                Category::printShortDescription
        );

        var offers = controller.getApp().getOffersStore().getOffers(leaf, this.requiredState);
        controller.signalListToView(offers, Offer::getOfferInfos);

    }

}
