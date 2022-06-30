package main.model;

import main.Application;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class UserCustomizer {

    public static String customizeConfigurator(@NotNull Application app, String oldname){
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
            app.getUserDataStore().updateUser(oldname, username, password);
        } else {
            Controller.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
        }
        return username;
    }

}
