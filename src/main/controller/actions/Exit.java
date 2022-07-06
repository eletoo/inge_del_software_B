package main.controller.actions;

import main.controller.Selectable;
import main.Application;
import main.exceptions.InvalidMethodException;
import main.model.User;

public class Exit implements Selectable {
    private String actionName;

    public Exit() {
        this.actionName ="Esci";
    }

    @Override
    public void runAction(Application app) {
        System.exit(0);
    }

    @Override
    public void runAction(Application app, User user) {
        throw new InvalidMethodException();
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
