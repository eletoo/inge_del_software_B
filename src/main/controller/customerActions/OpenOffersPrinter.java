package main.controller.customerActions;

import main.Application;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpenOffersPrinter implements Selectable {

    @Override
    public void runAction(@NotNull Application app) throws IOException {
        app.getOffersStore().viewOffersByCategory(app);
    }

    @Override
    public void runAction(Application app, User user) throws IOException {
        throw new InvalidMethodException();
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte per categoria";
    }
}
