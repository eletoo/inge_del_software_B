package main.model;

import main.Application;
import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
import main.controller.configuratorActions.Exit;
import main.controller.customerActions.*;
import main.controller.customerActions.OpenOffersPrinter;
import main.exceptions.InvalidMethodException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Customer extends User implements ListSelect {

    public Customer(String username, String password) {
        super(username, password);
        type = UserType.CUSTOMER;
    }

    @Override
    public void runUserMenu(Application app, User user) throws IOException {
        //todo: useAsFruitore()

        //carica i dati salvati in precedenza
        Controller.prepareStructures(app);

        //gestisce le offerte di scambio prima ancora di selezionare altre azioni
        Exchange.manageExchanges(app, fruitore);
        Exchange.managePastExchanges(app, fruitore);

        Selectable action;
        do {
            Controller.prepareStructures(app);

            action = choose(this.createMenu(), Selectable::getActionName);
            action.runAction(app, user);

        } while (! (action instanceof Exit));

    }

    @Override
    public void runUserMenu(Application app) {
        throw new InvalidMethodException();
    }

    private @NotNull List<Selectable> createMenu() {
        List<Selectable> menu = new LinkedList<>();
        menu.add(new AppContentPrinter());
        menu.add(new PersonalOffersPrinter());
        menu.add(new OpenOffersPrinter());
        menu.add(new OfferCreator());
        menu.add(new OfferDeleter());
        menu.add(new ExchangeCreator());
        menu.add(new LatestMessagePrinter());
        menu.add(new Exit());
        return menu;
    }
}
