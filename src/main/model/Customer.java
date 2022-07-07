package main.model;

import main.controller.Controller;
import main.controller.UserSelectable;
import main.controller.customerActions.*;
import main.controller.customerActions.OpenOffersPrinter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Customer extends User {

    public Customer(String username, String password) {
        super(username, password);
        type = UserType.CUSTOMER;
    }

    @Override
    public User onFirstLogin(Controller controller) {
        return this;
    }

    @Override
    public User onLogin(@NotNull Controller controller) throws IOException {
        controller.prepareStructures(controller.getApp());
        return controller.onUserLogin(this);
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
        menu.add(new main.controller.customerActions.Exit());
        return menu;
    }
}
