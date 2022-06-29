package main.controller;

import main.Application;

import java.io.IOException;

public interface Selectable {

    public void runAction(Application app) throws IOException;

    public String getActionName();
}
