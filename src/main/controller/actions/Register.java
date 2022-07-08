package main.controller.actions;

import main.controller.Controller;
import main.controller.CustomMessage;
import main.controller.Selectable;
import main.model.*;
import main.controller.GenericMessage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Gestisce la registrazione degli utenti
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class Register implements Selectable {
    private String actionName;

    public Register() {
        this.actionName = "Registrati";
    }

    /**
     * esegue la registrazione dell'utente facendogli selezionare il tipo di profilo da creare
     *
     * @param controller controller
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller) throws IOException {

        controller.getApp().getUserDataStore().load();

        if (controller.isFirstAccess())
            return;

        List<UserType> users = Arrays.stream(UserType.values()).collect(Collectors.toList());

        User user = controller.registerUser(controller.getView().choose(GenericMessage.SELECT_PROFILE_TYPE, users, u -> new CustomMessage(u.getUserType())));
        user.onLogin(controller).runUserMenu(controller);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return this.actionName;
    }

}
