package main.controller.customerActions;

import main.Application;
import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.Selectable;

import java.io.IOException;

public class Exit implements Selectable {
    @Override
    public void runAction(Application app) throws IOException {
        Controller.signalToView(GenericMessage.EXIT_MESSAGE.getMessage());
    }

    @Override
    public String getActionName() {
        return "Logout";
    }
}
