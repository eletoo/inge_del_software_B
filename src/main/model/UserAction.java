package main.model;

import main.controller.Controller;

import java.io.IOException;

public interface UserAction {

    void runUserMenu(Controller controller) throws IOException;
}
