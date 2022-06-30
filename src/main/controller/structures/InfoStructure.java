package main.controller.structures;

import main.Application;
import main.model.InfoScambio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class InfoStructure implements StructureLoader {

    @Override
    public void prepareStructure(Application app) throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var info = new File(System.getProperty("user.dir") + "/db/info.dat");
        if (info.exists()) {
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(info));
            try {
                this.setInfoScambio((InfoScambio) ois2.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setInfoScambio(null);
            }
        } else {

            this.setInfoScambio(null);
        }
    }
}
