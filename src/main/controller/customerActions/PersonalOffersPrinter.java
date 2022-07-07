package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PersonalOffersPrinter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
        //app.getOffersStore().viewPersonalOffers(user);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte personali";
    }
}
