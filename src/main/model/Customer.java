package main.model;

import main.Application;
import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
import main.controller.UserSelectable;
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
    public User onFirstLogin(Application app, Controller controller) {
        return this;
    }

    @Override
    public User onLogin(Application app, Controller controller) throws IOException {
        controller.prepareStructures(app);
        //        Exchange.manageExchanges(app, fruitore);
        //        Exchange.managePastExchanges(app, fruitore);
        return null;
    }

    protected List<UserSelectable> getUserMenu() {
        List<UserSelectable> menu = new LinkedList<>();
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
