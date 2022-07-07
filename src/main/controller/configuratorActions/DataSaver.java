package main.controller.configuratorActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DataSaver implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
        controller.getApp().save();
        controller.signalToView(GenericMessage.SAVED_CORRECTLY.getMessage());
    }

    @Override
    public String getActionName() {
        return "Salva";
    }

}
