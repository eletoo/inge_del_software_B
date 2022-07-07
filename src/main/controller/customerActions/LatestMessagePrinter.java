package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LatestMessagePrinter implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
//        //todo
//        if (app.getExchangesStore().getExchanges().isEmpty())
//            return;
//
//        var exc = choose(app.getExchangesStore()
//                .getExchanges()
//                .stream()
//                .filter(e -> e.getAuthor().equals(user) || e.getDest().equals(user))
//                .collect(Collectors.toList()), null
//        );
//
//        exc.viewLastMessageByCounterpart(user);

    }

    @Override
    public String getActionName() {
        return "Visualizza ultimi messaggi relativi ai tuoi scambi";
    }
}
