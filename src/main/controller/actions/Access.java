package main.controller.actions;

import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Access implements Selectable {
    private String actionName;

    public Access() {
        this.actionName = "Accedi";
    }

    @Override
    public void runAction(@NotNull Controller controller) throws IOException {

        if (controller.isFirstAccess())
            return;

        String username = controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
        if (controller.getApp().getUserDataStore().isUsernameTaken(username)) {

            if (controller.getApp().getUserDataStore().isLoginCorrect(username, controller.askStringFromView(GenericMessage.PASSWORD_REQUEST))) {
                controller.getApp().getUserDataStore().getUser(username).onLogin(controller).runUserMenu(controller);
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
