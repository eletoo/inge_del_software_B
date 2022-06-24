package main.controller;

import main.Application;

public interface Selectable {

    public void runAction(Application app);

    public String getActionName();
}
