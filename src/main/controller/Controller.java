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

        List<Selectable> selectable = Context.initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(selectable, Selectable::getActionName);
            selected.runAction(app);
        } while (!(selected instanceof Exit));
    }

}
