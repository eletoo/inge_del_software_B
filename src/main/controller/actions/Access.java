package main.controller.actions;

import main.controller.Selectable;
import main.Application;

public class Access implements Selectable {
    private String actionName;

    public Access() {
        this.actionName ="Accedi";
    }

    @Override
    public void runAction(Application app) {
        //DECISAMENTE DA CORREGGERE

        app.getUserDataStore().load();
        if (app.getUserDataStore().isEmpty()) {
            view.message("Non c'è alcun utente registrato -- crea un primo profilo Configuratore");
            controller.firstAccessAsConfiguratore();
        } else {
            String username = view.askUsername();
            if (controller.dataStore.isUsernameTaken(username)) {
                if (controller.dataStore.getUserMap().get(username) instanceof Configuratore) {
                    controller.secondAccessAsConfiguratore(username);
                } else if (controller.dataStore.getUserMap().get(username) instanceof Fruitore) {
                    controller.secondAccessAsFruitore(username);
                }
            } else {
                view.errorMessage(View.ErrorMessage.E_UNREGISTERED_USER);
            }
        }
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }
}
