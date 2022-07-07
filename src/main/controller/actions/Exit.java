package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;
import main.model.Application;

public class Exit implements Selectable {
    private String actionName;

    public Exit() {
        this.actionName ="Esci";
    }

    @Override
    public void runAction(Controller controller) {
        System.exit(0);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
