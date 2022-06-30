package main;

import main.model.HierarchiesStore;
import main.model.UserDataStore;

public class Application {
    private UserDataStore userDataStore = new UserDataStore();
    private HierarchiesStore hierarchiesStore = new HierarchiesStore();

    public UserDataStore getUserDataStore() {
        return this.userDataStore;
    }

    public HierarchiesStore getHierarchiesStore() {
        return this.hierarchiesStore;
    }
}
