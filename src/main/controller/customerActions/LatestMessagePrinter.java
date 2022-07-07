package main.controller.customerActions;

import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.Exchange;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

public class LatestMessagePrinter implements UserSelectable {
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        if (controller.getApp().getExchangesStore().getExchanges().isEmpty())
            return;

        var exc = controller.getView().choose(
                controller.getApp().getExchangesStore().getExchanges().stream()
                .filter(e -> e.getAuthor().equals(user) || e.getDest().equals(user))
                .collect(Collectors.toList()), Exchange::getExchangeDescription
        );
        var msg = exc.getLastMessageByCounterpart(user);

        controller.signalToView( exc.getSelectedOffer().getName() + " <--> " + exc.getOwnOffer().getName() + ": " + (msg.getMessage() != null ? msg.getMessage() : "--"));
    }

    @Override
    public String getActionName() {
        return "Visualizza ultimi messaggi relativi ai tuoi scambi";
    }
}
