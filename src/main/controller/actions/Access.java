package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;
import main.Application;
import main.exceptions.InvalidMethodException;
import main.model.Registration;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Access implements Selectable {
    private String actionName;

    public Access() {
        this.actionName = "Accedi";
    }

    @Override
    public void runAction(@NotNull Application app) throws IOException {

        if (Registration.firstAccessEver(app))
            return;

        String username = Controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
        if (app.getUserDataStore().isUsernameTaken(username)) {

            if (app.getUserDataStore().isLoginCorrect(username, Controller.askStringFromView(GenericMessage.PASSWORD_REQUEST))) {
                app.getUserDataStore().getUser(username).runUserMenu(app);
                return;
            }

            Controller.signalToView(ErrorMessage.E_WRONG_PASSWORD.getMessage());
            return;
        }

        Controller.signalToView(ErrorMessage.E_UNREGISTERED_USER.getMessage());

    }


    @Override
    public void runAction(Application app, User user) {
        throw new InvalidMethodException();
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
