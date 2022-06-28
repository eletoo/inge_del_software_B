package main.model;

import main.Application;
import main.controller.Controller;
import main.view.ErrorMessage;
import main.view.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class Logger {

    public static boolean loginCurrentConfigurator(@NotNull Application app) {

        boolean auth;
        String username;
        do {
            username = Controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
            auth = app.getUserDataStore().isLoginCorrect(username, Controller.askStringFromView(GenericMessage.PASSWORD_REQUEST));

            if (auth) {
                UserCustomizer.customizeConfiguratore(app, username);
            } else {
                Controller.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
            }
        } while (!auth);

        return true;
    }

}
