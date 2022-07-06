package main.controller;

import main.Application;
import main.controller.actions.Exit;
import main.controller.structures.*;
import main.model.*;
import main.view.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

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

    public static <T> void signalListToView(@NotNull List<T> toPrint, Function<T, String> toApply){
        toPrint.forEach(e -> signalToView(toApply == null ? e.toString() : toApply.apply(e)));
    }

    public static String askStringFromView(Message message) {
        return (new StringReaderClass()).in(message);
    }

    public static String askPotentiallyEmptyStringFromView(Message message){
        return (new StringReaderClass()).inPotentiallyEmptyLine(message);
    }

    public static String askLineFromVew(Message message){
        return (new StringReaderClass()).inLine(message);
    }

    public static int askIntFromView(Message message) {
        return (new IntegerReader().in(message));
    }

    public static boolean askBooleanFromView(Message message){
        String ans;
        do {
            ans = askStringFromView(message);
        } while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n"));

        return ans.equalsIgnoreCase("y");
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
    }

}
