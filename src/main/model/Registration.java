package main.model;

import main.Application;
import main.controller.Controller;
import main.view.ErrorMessage;
import main.view.GenericMessage;
import org.jetbrains.annotations.NotNull;

public class Registration {

    /**
     * Registra un utente di cui viene specificato il tipo.
     * Se ci sono nuovi tipi di utente basta aggiungere il tipo corrispondente nell'enum {@link UserType} e un caso
     * allo switch in questo metodo.
     *
     * @param userType tipo dell'utente
     * @param app      applicazione
     */
    public static void registerUser(@NotNull UserType userType, Application app) {
        User user = null;
        switch (userType) {
            case CONFIGURATOR:
                user = registerConfigurator(app);
                break;
            case CUSTOMER:
                user = registerCustomer(app);
                break;
        }
        user.runUserMenu();
        //todo: rifare perché dipende dai tipi e non è chiuso alla modifica
    }

    private static @NotNull User registerConfigurator(@NotNull Application app) {

        Controller.signalToView(ErrorMessage.E_EMPTY_USER_DB.getMessage());
        Controller.signalToView(GenericMessage.STD_CREDENTIALS_MSG.getMessage());

        String username;
        do {
            username = RandomItemGenerator.generateRandomString(10);
        } while (app.getUserDataStore().isUsernameTaken(username));

        String pw = RandomItemGenerator.generateRandomPassword(10);

        Controller.signalToView("Username: " + username);
        Controller.signalToView("Password: " + pw);

        User c = new Configurator(username, pw);
        app.getUserDataStore().addUser(c);
        return c;
    }

    private static @NotNull User registerCustomer(@NotNull Application app) {
        String username;
        do {
            username = Controller.askStringFromView(GenericMessage.USERNAME_REQUEST);
            if (app.getUserDataStore().isUsernameTaken(username))
                Controller.signalToView(ErrorMessage.E_USERNAME_TAKEN.getMessage());

        } while (app.getUserDataStore().isUsernameTaken(username));

        User f = new Customer(username, Controller.askStringFromView(GenericMessage.PASSWORD_REQUEST));
        app.getUserDataStore().addUser(f);
        return f;
    }
}
