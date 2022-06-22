package controller;

import controller.actions.Access;
import controller.actions.Exit;
import controller.actions.Register;
import model.*;
import org.jetbrains.annotations.NotNull;
import view.*;
import view.actions.*;

import java.io.IOException;
import java.util.*;

public class Controller {

    //parla con la view

    private Controller() throws IOException {
    }

    public static void run() throws IOException {
        Controller c = new Controller();
        //fa cose

        LocalPath.createLocalDirectories();

        ActionSelection sel = new ActionSelection();

        List<Selectable> selectable = initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(selectable);//seleziono l'opzione da eseguire
            selected.runAction();
        } while (!(selected instanceof Exit));
    }

    private static @NotNull List<Selectable> initiateSelectableList() {
        List<Selectable> initialSelections = new LinkedList<>();
        initialSelections.add(new Access());
        initialSelections.add(new Register());
        initialSelections.add(new Exit());
        return initialSelections;
    }

}
