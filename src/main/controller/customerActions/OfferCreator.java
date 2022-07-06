package main.controller.customerActions;

import main.Application;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.Customer;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OfferCreator implements Selectable {
    @Override
    public void runAction(Application app) throws IOException {
        throw new InvalidMethodException();
    }

    @Override
    public void runAction(@NotNull Application app, User user) throws IOException {
        app.getOffersStore().createOffer(app, (Customer) user);
    }

    @Override
    public String getActionName() {
        return "Crea offerta";
    }
}
