package controller.actions;

public class Exit extends ExecutableAction{
    private String message;

    public Exit() {
        this.message="Esci";
    }

    @Override
    public void runAction() {
        //DECISAMENTE DA CORREGGERE

        view.interactionMessage(View.InteractionMessage.EXIT_MESSAGE);
    }
}
