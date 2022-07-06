package main.model;

import main.Application;

import java.io.IOException;

public interface UserAction {

    void runUserMenu(Application app, User user) throws IOException;

    void runUserMenu(Application app) throws IOException;

}
