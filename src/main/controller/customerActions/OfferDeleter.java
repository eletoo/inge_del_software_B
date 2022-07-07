package main.controller.customerActions;

import main.controller.ErrorMessage;
import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.Offer;
import main.model.OfferState;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

public class OfferDeleter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
        var user_offers = controller.getApp().getOffersStore().getOffers(user)
                .stream()
                .filter(e -> e.getState() == OfferState.APERTA)
                .collect(Collectors.toList());

        if (user_offers.isEmpty()) {
            controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
        } else {
            var to_edit = (Offer) controller.getView().choose(user_offers, Offer::getOfferInfos);
            to_edit.setState(OfferState.RITIRATA);
            controller.getApp().getOffersStore().save();
        }
    }

    @Override
    public String getActionName() {
        return "Ritira offerta";
    }
}
