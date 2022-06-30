package main.controller.structures;

import main.Application;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class DirectoryStructure implements StructureLoader{

    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
       app.getHierarchiesStore().load();

    }
}
