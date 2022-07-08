package main.controller;

import main.view.View;

import java.util.List;
import java.util.function.Function;

/**
 * interfaccia con metodo da implementare per selezionare un'azione
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public interface Select {

    Selectable selectAction(View view, List<Selectable> selectableList, Function<Selectable, Message> function);
}
