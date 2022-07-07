package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OfferDeleter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
        //app.getOffersStore().undoOffer(app, user);
    }

    @Override
    public String getActionName() {
        return "Ritira offerta";
    }
}
