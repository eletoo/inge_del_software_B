package tests;

import main.controller.Controller;
import main.model.Application;
import main.model.RegistrationHandler;
import main.model.UserType;
import main.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDataStoreTest {
    Application app = new Application();
    RegistrationHandler rh = new RegistrationHandler(app);
    Controller controller = new Controller(app, new View());

    @Test
    void isUsernameTaken() {
        rh.registerUser(UserType.CUSTOMER, "nome", "password", controller);
        assertTrue(app.getUserDataStore().isUsernameTaken("nome"));
    }

    @Test
    void updateUser() {
        rh.registerUser(UserType.CUSTOMER, "nome", "password", controller);
        app.getUserDataStore().updateUser("nome", "newnome", "newpassword");
        assertTrue(app.getUserDataStore().getUser("newnome").getUsername().equals("newnome"));
    }

    @Test
    void loginCorrectWithCorrectCredentials() {
        rh.registerUser(UserType.CUSTOMER, "nome", "password", controller);
        assertTrue(app.getUserDataStore().isLoginCorrect("nome", "password"));
    }

    @Test
    void loginIncorrectWithIncorrectUsername() {
        rh.registerUser(UserType.CUSTOMER, "nome", "password", controller);
        assertFalse(app.getUserDataStore().isLoginCorrect("newnome", "password"));
    }

    @Test
    void loginIncorrectWithIncorrectPassword() {
        rh.registerUser(UserType.CUSTOMER, "nome", "password", controller);
        assertFalse(app.getUserDataStore().isLoginCorrect("nome", "newpassword"));
    }

    @Test
    void isEmpty() {
        assertTrue(app.getUserDataStore().isEmpty());
    }
}