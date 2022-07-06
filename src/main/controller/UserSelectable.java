package main.controller;

import main.Application;
import main.model.Configurator;
import main.model.Customer;
import main.model.User;

import java.io.IOException;

public interface UserSelectable {

    void runAction(Application app, Controller controller, User user) throws IOException;

    String getActionName();
}
