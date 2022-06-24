package main.controller.actions;

import main.controller.Selectable;
import main.Application;
import main.model.Registration;

public class Register implements Selectable {
    private String actionName;

    public Register() {
        this.actionName ="Registrati";
    }

    @Override
    public void runAction(Application app) {
        Registration.registerUser(app);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
