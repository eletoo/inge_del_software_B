package main.controller.customerActions;

import main.Application;
import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.Selectable;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Exit implements UserSelectable {
    @Override
    public void runAction(@NotNull Application app, Controller controller, User user) throws IOException {
        Controller.signalToView(GenericMessage.EXIT_MESSAGE.getMessage());
    }

    @Override
    public String getActionName() {
        return "Logout";
    }
}
