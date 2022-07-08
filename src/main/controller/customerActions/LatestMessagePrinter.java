package main.controller.customerActions;

import main.controller.Controller;
import main.controller.CustomMessage;
import main.controller.UserSelectable;
import main.model.Exchange;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * stampa l'ultimo messaggio della controparte
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class LatestMessagePrinter implements UserSelectable {
    /**
     * stampa l'ultimo messaggio della controparte
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        if (controller.getApp().getExchangesStore().getExchanges().isEmpty())
            return;

        var exc = controller.getView().choose(
                controller.getApp().getExchangesStore().getExchanges().stream()
                        .filter(e -> e.getAuthor().equals(user) || e.getDest().equals(user))
                        .collect(Collectors.toList()), e -> controller.getView().getExchangedOffersDescription(e.getExchangeDescription())
        );
        var msg = exc.getLastMessageByCounterpart(user);

        controller.signalToView(new CustomMessage(
                        exc.getSelectedOffer().getName()
                                + " <--> " + exc.getOwnOffer().getName()
                                + ": "
                                + (msg.getMessage() != null ? msg.getMessage() : "--")
                )
        );
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Visualizza ultimi messaggi relativi ai tuoi scambi";
    }
}
