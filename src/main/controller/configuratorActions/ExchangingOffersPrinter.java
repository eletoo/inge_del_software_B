package main.controller.configuratorActions;

import main.Application;
import main.controller.Controller;
import main.controller.Selectable;
import main.controller.UserSelectable;
import main.exceptions.InvalidMethodException;
import main.model.OfferState;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ExchangingOffersPrinter implements UserSelectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller, User user) throws IOException {
        app.getOffersStore().viewOffers(app, OfferState.IN_SCAMBIO);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte in scambio";
    }

}
