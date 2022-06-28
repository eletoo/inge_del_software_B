package main.controller.actions;

import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
import main.Application;
import main.model.*;
import main.view.GenericMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Register implements Selectable, ListSelect {
    private String actionName;

    public Register() {
        this.actionName = "Registrati";
    }

    @Override
    public void runAction(@NotNull Application app) {

        app.getUserDataStore().load();

        if (app.getUserDataStore().isEmpty()) {

            Registration.registerUser(UserType.CONFIGURATOR, app);

            if (Logger.loginCurrentConfigurator(app))
                //useAsConfiguratore();
                //todo: implementare useAsConfiguratore()
                ;
            return;
        }

        Controller.signalToView(GenericMessage.SELECT_PROFILE_TYPE.getMessage());
        List<UserType> users = Arrays.stream(UserType.values()).collect(Collectors.toList());

        Registration.registerUser(choose(users, UserType::getUserType), app);

    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

}
