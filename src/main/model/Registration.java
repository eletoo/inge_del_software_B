package main.model;

import main.Application;
import org.jetbrains.annotations.NotNull;

public class Registration {

    public static void registerUser(@NotNull Application app){

        app.getUserDataStore().load();

        if (app.getUserDataStore().isEmpty()) {
            view.message("Non c'è alcun utente registrato -- crea un primo profilo Configuratore");
            controller.firstAccessAsConfiguratore();
        } else {
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
            }
        }
    }
}
