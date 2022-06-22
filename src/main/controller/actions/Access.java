package controller.actions;

public class Access extends ExecutableAction{
    private String message;

    public Access() {
        this.message="Accedi";
    }

    @Override
    public void runAction() {
        //DECISAMENTE DA CORREGGERE

        controller.dataStore.load();
        if (controller.dataStore.isEmpty()) {
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
}
