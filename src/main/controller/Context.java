package main.controller;

import main.controller.actions.Access;
import main.controller.actions.Exit;
import main.controller.actions.Register;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Context del pattern Strategy: aggrega oggetti Selectable (le singole strategy)
 * Si tratta dell'unica classe da modificare quando voglio aggiungere una strategy di accesso alla app
 */
public class Context {

    static @NotNull List<Selectable> initiateSelectableList() {
        List<Selectable> initialSelections = new LinkedList<>();
        initialSelections.add(new Access());
        initialSelections.add(new Register());
        initialSelections.add(new Exit());
        return initialSelections;
    }

}
