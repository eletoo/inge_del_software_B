package main;

import main.model.UserDataStore;

public class Application {
    UserDataStore userDataStore = new UserDataStore();


    public UserDataStore getUserDataStore(){
        return this.userDataStore;
    }


}
