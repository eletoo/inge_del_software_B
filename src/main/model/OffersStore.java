package main.model;

import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class OffersStore implements Loadable, Saveable, Serializable {
    private List<Offer> offers;

    public OffersStore() {
        offers = new LinkedList<>();
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var of = new File(System.getProperty("user.dir") + "/db/offerte.dat");
        if (of.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(of));
            try {
                this.setOffers((List<Offer>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setOffers(new LinkedList<>());
            }
        } else {
            this.setOffers(new LinkedList<>());
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        throw new NonLoadableFromFileException();
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/db/offerte.dat"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getOffers());
        objectOutputStream.close();
    }

    @Override
    public void saveOnFile(Serializable s) {
        throw new NonSaveableOnFileException();
    }
}
