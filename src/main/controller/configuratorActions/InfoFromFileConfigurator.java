package main.controller.configuratorActions;

import main.controller.Controller;
import main.controller.CustomMessage;
import main.controller.GenericMessage;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * configura le informazioni di scambio da file
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class InfoFromFileConfigurator implements UserSelectable {
    /**
     * configura le informazioni di scambio da file
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) {
        try {
            controller.getApp().getInformationStore().loadFromFile();
            controller.signalToView(GenericMessage.SUCCESSFUL_IMPORT);
        } catch (IOException e) {
            controller.signalToView(new CustomMessage(e.getMessage()));
        }
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Configura informazioni di scambio da file";
    }
}
