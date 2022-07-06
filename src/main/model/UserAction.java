package main.model;

import main.Application;
import main.controller.Controller;

import java.io.IOException;

public interface UserAction {

    void runUserMenu(Application app, Controller controller) throws IOException;
}
