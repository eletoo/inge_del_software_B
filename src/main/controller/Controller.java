package main.controller;

import main.Application;
import main.controller.actions.Exit;
import main.controller.structures.*;
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

    public static String askStringFromView(GenericMessage message) {
        return (new StringReaderClass()).in(message);
    }

    public static int askIntFromView(GenericMessage message) {
        return (new IntegerReader().in(message));
    }

    public static void prepareStructures(Application app) throws IOException {
        StructureLoader s = new DirectoryStructure();
        s.prepareStructure(app);
        s = new InfoStructure();
        s.prepareStructure(app);
        s = new OfferStructure();
        s.prepareStructure(app);
        s = new ExchangeStructure();
        s.prepareStructure(app);
        //todo: metodo prepareStructure() che richiama il load() di <Sthg>Store (analoga per tutti gli Store)
    }

}
