package main.controller;

import main.Application;
import main.controller.actions.Exit;
import main.model.*;
import main.view.*;

import java.io.IOException;
import java.util.*;

public class Controller {

    //parla con la view

    public static void run() throws IOException {

        Application app = new Application();

        LocalPath.createLocalDirectories();

        ActionSelection sel = new ActionSelection();
        Context context = new Context();

        List<Selectable> selectable = Context.initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(selectable, Selectable::getActionName);
            selected.runAction(app);
        } while (!(selected instanceof Exit));
    }

    public static void signalToView(String message) {
        MessagePrinter.printText(message);
    }

    //TODO: non mi piace chiamare un nuovo StringReaderClass
    public static String askStringFromView(GenericMessage message) {
        return (new StringReaderClass()).in(message);
    }

    //TODO: non mi piace chiamare un nuovo StringReaderClass
    public static int askIntFromView(GenericMessage message) {
        return (new IntegerReader().in(message));
    }

}
