package main.controller.structures;

import main.Application;
import main.model.Exchange;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class ExchangeStructure implements StructureLoader {

    @Override
    public void prepareStructure(Application app) throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var exchanges = new File(System.getProperty("user.dir") + "/db/scambi.dat");
        if (exchanges.exists()) {
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(exchanges));
            try {
                this.setScambi((List<Exchange>) ois2.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setScambi(new LinkedList<>());
            }
        } else {
            this.setScambi(new LinkedList<>());
        }
    }
}
