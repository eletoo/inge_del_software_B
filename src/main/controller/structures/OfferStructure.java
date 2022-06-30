package main.controller.structures;

import main.Application;
import main.model.Offer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;

public class OfferStructure implements StructureLoader {
    @Override
    public void prepareStructure(Application app) throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var of = new File(System.getProperty("user.dir") + "/db/offerte.dat");
        if (of.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(of));
            try {
                this.setOfferte((List<Offer>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setOfferte(new LinkedList<>());
            }
        } else {
            this.setOfferte(new LinkedList<>());
        }
    }
}
