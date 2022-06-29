package main.model;

import main.Application;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class Logger {

    public static User loginFirstConfigurator(@NotNull Application app) {

        boolean auth;
        String username;
        String newName = "";
        do {
            username = Controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
            auth = app.getUserDataStore().isLoginCorrect(username, Controller.askStringFromView(GenericMessage.PASSWORD_REQUEST));

            if (auth) {
                newName = UserCustomizer.customizeConfigurator(app, username);
            } else {
                Controller.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
            }
        } while (!auth);

        return app.getUserDataStore().getUser(newName);
    }

}
