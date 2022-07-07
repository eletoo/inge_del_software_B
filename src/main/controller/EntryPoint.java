package main.controller;

import main.model.Application;
import main.model.LocalPath;
import main.view.View;

import java.io.IOException;

/**
 * Classe contenente il metodo main
 *
 * @author Elena Tonini, Mattia Pavlovic, Claudia Manfredi
 */
public class EntryPoint {

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        LocalPath.createLocalDirectories();
        View view = new View();
        Controller controller = new Controller(app, view);
        controller.run();
    }

}