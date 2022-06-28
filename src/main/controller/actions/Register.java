package main.controller.actions;

import main.controller.Selectable;
import main.Application;
import main.model.*;
import org.jetbrains.annotations.NotNull;

public class Register implements Selectable {
    private String actionName;

    public Register() {
        this.actionName = "Registrati";
    }

    @Override
    public void runAction(@NotNull Application app) {

        app.getUserDataStore().load();

        if (app.getUserDataStore().isEmpty()) {

            Registration.registerUser(app);

            if(Logger.loginCurrentConfigurator(app))
                //useAsConfiguratore();
                //todo: implementare useAsConfiguratore()
                ;
            return;
        }

        //todo: else seleziona modalità di registrazione
        /*
        String choice = view.in("Seleziona la modalità con cui vuoi registrarti:\n1. Configuratore\n2. Fruitore");
        switch (choice) {
            case "1": {
                controller.firstAccessAsConfiguratore();
            }
            break;
            case "2": {
                controller.firstAccessAsFruitore();
            }
            break;
            default:
                view.errorMessage(View.ErrorMessage.E_UNAUTHORIZED_CHOICE);

    }*/


    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
