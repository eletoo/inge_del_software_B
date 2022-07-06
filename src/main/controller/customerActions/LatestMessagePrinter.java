package main.controller.customerActions;

import main.Application;
import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.stream.Collectors;

public class LatestMessagePrinter implements Selectable, ListSelect {
    @Override
    public void runAction(@NotNull Application app, Controller controller) throws IOException {
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
