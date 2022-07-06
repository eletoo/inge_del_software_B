package main.controller;

import main.Application;
import main.model.User;

import java.io.IOException;

public interface Selectable {

    void runAction(Application app, Controller controller) throws IOException;

    String getActionName();
}
