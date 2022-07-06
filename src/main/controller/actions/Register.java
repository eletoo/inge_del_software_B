package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;
import main.Application;
import main.exceptions.InvalidMethodException;
import main.model.*;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Register implements Selectable {
    private String actionName;

    public Register() {
        this.actionName = "Registrati";
    }

    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {

        app.getUserDataStore().load();

        if(controller.isFirstAccess())
            return;

        controller.signalToView(GenericMessage.SELECT_PROFILE_TYPE.getMessage());
        List<UserType> users = Arrays.stream(UserType.values()).collect(Collectors.toList());

        User user = controller.registerUser(UserType.CUSTOMER);
        user.runUserMenu(app, controller);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
