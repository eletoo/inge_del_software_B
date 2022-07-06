package main.controller.customerActions;

import main.Application;
import main.controller.Controller;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OfferDeleter implements Selectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {
        //app.getOffersStore().undoOffer(app, user);
    }

    @Override
    public String getActionName() {
        return "Ritira offerta";
    }
}
