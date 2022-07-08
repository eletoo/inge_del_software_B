package main.controller.structures;

import main.model.Application;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * carica le informazioni di scambio salvate
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class InfoStructure implements StructureLoader {

    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
        app.getInformationStore().load();
    }
}
