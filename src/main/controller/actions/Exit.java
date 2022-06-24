package main.controller.actions;

import main.controller.Selectable;
import main.Application;

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
    public String getActionName() {
        return this.actionName;
    }
}
