package main.model;

import main.model.stores.*;

import java.io.IOException;
import java.io.Serializable;

public class Application implements Saveable, Loadable {
    private UserDataStore userDataStore = new UserDataStore();
    private HierarchiesStore hierarchiesStore = new HierarchiesStore();
    private InformationStore infoStore = new InformationStore();
    private OffersStore offersStore = new OffersStore();
    private ExchangesStore exchangesStore = new ExchangesStore();

    public UserDataStore getUserDataStore() {
        return this.userDataStore;
    }

    public HierarchiesStore getHierarchiesStore() {
        return this.hierarchiesStore;
    }

    public InformationStore getInformationStore() {
        return this.infoStore;
    }

    public OffersStore getOffersStore() {
        return this.offersStore;
    }

    public ExchangesStore getExchangesStore() {
        return exchangesStore;
    }

    @Override
    public void load() throws IOException {
        this.userDataStore.load();
        this.infoStore.load();
        this.hierarchiesStore.load();
        this.offersStore.load();
        this.exchangesStore.load();
    }

    @Override
    public void loadFromFile() throws IOException {
        this.infoStore.loadFromFile();
        this.hierarchiesStore.loadFromFile();
    }

    @Override
    public void save() throws IOException {
        this.userDataStore.save();
        this.infoStore.save();
        this.hierarchiesStore.save();
        this.offersStore.save();
        this.exchangesStore.save();
    }

    @Override
    public void saveOnFile(Serializable s) {
        this.infoStore.saveOnFile(s);
        for (Hierarchy h : hierarchiesStore.getHierarchies().values()) {
            this.hierarchiesStore.saveOnFile(h);
        }

    }
}
