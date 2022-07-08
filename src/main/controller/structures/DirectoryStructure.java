package main.controller.structures;

import main.model.Application;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * carica le gerarchie salvate
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class DirectoryStructure implements StructureLoader {

    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
        app.getHierarchiesStore().load();
    }
}
