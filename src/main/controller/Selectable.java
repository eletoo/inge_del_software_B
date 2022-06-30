package main.controller;

import main.Application;

import java.io.IOException;

public interface Selectable {

    void runAction(Application app) throws IOException;

    String getActionName();
}
