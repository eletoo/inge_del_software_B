package main.controller.structures;

import main.model.Application;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * carica le offerte salvate
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class OfferStructure implements StructureLoader {
    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
        app.getOffersStore().load();
    }
}
