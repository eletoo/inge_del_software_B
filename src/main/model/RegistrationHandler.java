package main.model;

import main.controller.Controller;
import org.jetbrains.annotations.NotNull;

public class RegistrationHandler {

    private Application app;

    public RegistrationHandler(Application app) {
        this.app = app;
    }

    public User registerUser(@NotNull UserType userType, String username, String pw, Controller controller) {
        User user = null;
        switch (userType) {
            case CONFIGURATOR:
                user = registerConfigurator(username, pw, controller);
                break;
            case CUSTOMER:
                user = registerCustomer(username, pw, controller);
                break;
        }
        return user;
    }

    private @NotNull User registerConfigurator(String username, String pw, Controller controller) {
        User c = new Configurator(username, pw);
        app.getUserDataStore().addUser(c);

        return c.onFirstLogin(controller);
    }

    private @NotNull User registerCustomer(String username, String pw, Controller controller) {
        User f = new Customer(username, pw);
        app.getUserDataStore().addUser(f);
        return f.onFirstLogin(controller);
    }
}
