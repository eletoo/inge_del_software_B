package main.controller.actions;

import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
import main.Application;
import main.model.*;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Register implements Selectable, ListSelect {
    private String actionName;

    public Register() {
        this.actionName = "Registrati";
    }

    @Override
    public void runAction(@NotNull Application app) throws IOException {

        app.getUserDataStore().load();

        if(Registration.firstAccessEver(app))
            return;

        Controller.signalToView(GenericMessage.SELECT_PROFILE_TYPE.getMessage());
        List<UserType> users = Arrays.stream(UserType.values()).collect(Collectors.toList());

        User user = Registration.registerUser(choose(users, UserType::getUserType), app);
        user.runUserMenu();

    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
