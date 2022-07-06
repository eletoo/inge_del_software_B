package main.controller.actions;

import main.controller.Controller;
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
    public void runAction(Application app, Controller controller) {
        System.exit(0);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
