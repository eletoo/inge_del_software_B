package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.User;

import java.io.IOException;

public class Exit implements UserSelectable {

    @Override
    public void runAction(Controller controller, User user) throws IOException {
        controller.signalToView(GenericMessage.EXIT_MESSAGE.getMessage());
    }

    @Override
    public String getActionName() {
        return "Logout";
    }

}
