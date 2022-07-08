package main.controller.structures;

import main.model.Application;

import java.io.IOException;

/**
 * interfaccia per il caricamento di una struttura di salvataggio dati
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public interface StructureLoader {

    void prepareStructure(Application app) throws IOException;
}
