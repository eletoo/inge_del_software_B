package main.controller.structures;

import main.Application;

import java.io.IOException;

public interface StructureLoader {

    void prepareStructure(Application app) throws IOException;
}