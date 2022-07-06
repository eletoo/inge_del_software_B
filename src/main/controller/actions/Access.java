package main.controller.actions;

import main.controller.Controller;
import main.controller.Selectable;
import main.Application;
import main.model.RegistrationHandler;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Access implements Selectable {
    private String actionName;

    public Access() {
        this.actionName = "Accedi";
    }

    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {

        if (controller.isFirstAccess())
            return;

        String username = controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
        if (app.getUserDataStore().isUsernameTaken(username)) {

            if (app.getUserDataStore().isLoginCorrect(username, controller.askStringFromView(GenericMessage.PASSWORD_REQUEST))) {
                app.getUserDataStore().getUser(username).onLogin(app, controller).runUserMenu(app, controller);
                return;
            }

            controller.signalToView(ErrorMessage.E_WRONG_PASSWORD.getMessage());
            return;
        }

        controller.signalToView(ErrorMessage.E_UNREGISTERED_USER.getMessage());

    }
    @Override
    public String getActionName() {
        return this.actionName;
    }
}
