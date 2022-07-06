package main.controller;

import main.Application;
import main.model.User;

import java.io.IOException;

public interface Selectable {

    void runAction(Application app) throws IOException;

    void runAction(Application app, User user) throws IOException;

    String getActionName();
}
