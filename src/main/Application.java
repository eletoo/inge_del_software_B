package main;

import main.model.stores.*;

public class Application {
    private UserDataStore userDataStore = new UserDataStore();
    private HierarchiesStore hierarchiesStore = new HierarchiesStore();
    private InformationStore infoStructure = new InformationStore();
    private OffersStore offersStore = new OffersStore();
    private ExchangesStore exchangesStore = new ExchangesStore();

    public UserDataStore getUserDataStore() {
        return this.userDataStore;
    }

    public HierarchiesStore getHierarchiesStore() {
        return this.hierarchiesStore;
    }

    public InformationStore getInformationStore() {
        return this.infoStructure;
    }

    public OffersStore getOffersStore(){
        return this.offersStore;
    }

    public ExchangesStore getExchangesStore() {
        return exchangesStore;
    }
}
