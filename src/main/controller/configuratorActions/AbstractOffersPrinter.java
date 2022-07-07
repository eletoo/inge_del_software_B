package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.*;

import java.io.IOException;

public abstract class AbstractOffersPrinter implements UserSelectable {

    private OfferState requiredState;

    public AbstractOffersPrinter(OfferState requiredState) {
        this.requiredState = requiredState;
    }

    /**
     * Stampa tutte le offerte di tutti gli utenti con stato chiuso
     * @param controller .
     * @param user .
     * @throws IOException
     */
    @Override
    public void runAction(Controller controller, User user) throws IOException {

        if (controller.getApp().getHierarchiesStore().getHierarchies().size() == 0) {
            Controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        Leaf leaf = controller.getView().choose(
                GenericMessage.SELECT_CATEGORY,
                controller.getApp().getOffersStore().getLeafCategories(controller.getApp()),
                Category::printShortDescription
        );

        var offers = controller.getApp().getOffersStore().viewOffers(leaf, this.requiredState);
        controller.signalListToView(offers, Offer::getOfferInfos);
    }

}
