package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;
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
    public void runAction(@NotNull Controller controller) throws IOException {

        controller.getApp().getUserDataStore().load();

        if (controller.isFirstAccess())
            return;

        List<UserType> users = Arrays.stream(UserType.values()).collect(Collectors.toList());

        User user = controller.registerUser(controller.getView().choose(GenericMessage.SELECT_PROFILE_TYPE, users, UserType::getUserType));
        user.runUserMenu(controller);
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
