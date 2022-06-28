package main.model;

import main.Application;
import main.controller.Controller;
import main.view.ErrorMessage;
import main.view.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class Registration {

    public static void registerUser(@NotNull Application app){

        Controller.signalToView(ErrorMessage.E_EMPTY_USER_DB.getMessage());
        Controller.signalToView(GenericMessage.STD_CREDENTIALS_MSG.getMessage());

        String username;
        do{
            username = RandomItemGenerator.generateRandomString(10);
        }while(app.getUserDataStore().isUsernameTaken(username));

        String pw = RandomItemGenerator.generateRandomPassword(10);

        Controller.signalToView(username);
        Controller.signalToView(pw);

        app.getUserDataStore().addUser(new Configuratore(username, pw));

    }

}
