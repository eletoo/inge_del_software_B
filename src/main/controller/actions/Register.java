package controller.actions;

public class Register extends ExecutableAction{
    private String message;

    public Register() {
        this.message="Registrati";
    }

    @Override
    public void runAction() {
        //DECISAMENTE DA CORREGGERE

        controller.dataStore.load();
        if (controller.dataStore.isEmpty()) {
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
