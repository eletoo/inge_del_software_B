package main.controller.customerActions;

import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.Offer;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PersonalOffersPrinter implements UserSelectable {
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        controller.signalListToView(
                controller.getApp().getOffersStore().getOffers(user),
                Offer::getOfferInfos
        );
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte personali";
    }
}
