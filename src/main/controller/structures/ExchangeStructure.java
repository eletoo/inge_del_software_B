package main.controller.structures;

import main.model.Application;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * carica gli scambi salvati
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class ExchangeStructure implements StructureLoader {

    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
        app.getExchangesStore().load();
    }
}
