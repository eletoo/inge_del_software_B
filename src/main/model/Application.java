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

    public OffersStore getOffersStore(){
        return this.offersStore;
    }

    public ExchangesStore getExchangesStore() {
        return exchangesStore;
    }

    @Override
    public void load() throws IOException {
        this.exchangesStore.load();
        this.offersStore.load();
        this.userDataStore.load();
        this.infoStore.load();
        this.hierarchiesStore.load();
    }

    @Override
    public void loadFromFile() throws IOException {
        this.infoStore.loadFromFile();
        this.hierarchiesStore.loadFromFile();
    }

    @Override
    public void save() throws IOException {
        this.exchangesStore.save();
        this.offersStore.save();
        this.userDataStore.save();
        this.infoStore.save();
        this.hierarchiesStore.save();
    }

    @Override
    public void saveOnFile(Serializable s) {
        this.infoStore.saveOnFile(s);
        this.hierarchiesStore.saveOnFile(s);
    }
}
