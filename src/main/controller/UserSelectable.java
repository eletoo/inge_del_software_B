package main.controller;

import main.model.User;

import java.io.IOException;

public interface UserSelectable {

    void runAction(Controller controller, User user) throws IOException;

    String getActionName();
}
