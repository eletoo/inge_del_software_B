package main.controller.customerActions;

import main.Application;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PersonalOffersPrinter implements Selectable {

    @Override
    public void runAction(@NotNull Application app) throws IOException {
        throw new InvalidMethodException();
    }

    @Override
    public void runAction(@NotNull Application app, User user) {
        app.getOffersStore().viewPersonalOffers(user);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte personali";
    }
}
