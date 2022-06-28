package main.model;

import main.Application;
import main.controller.Controller;
import main.view.ErrorMessage;
import main.view.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class UserCustomizer {

    public static void customizeConfiguratore(@NotNull Application app, String currentUsername){
        Controller.signalToView(GenericMessage.CUSTOMIZE_CREDENTIALS.getMessage());

        String username;
        do {
            username = Controller.askStringFromView(GenericMessage.CUSTOMIZE_USERNAME);
            if (app.getUserDataStore().isUsernameTaken(username)) {
                Controller.signalToView(ErrorMessage.E_USERNAME_TAKEN.getMessage());
            }
        } while (app.getUserDataStore().isUsernameTaken(username));

        String password = Controller.askStringFromView(GenericMessage.CUSTOMIZE_PW);

        if (password != null && username != null) {
            app.getUserDataStore().updateUser(currentUsername, username, password);
        } else {
            Controller.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
        }
    }
}
