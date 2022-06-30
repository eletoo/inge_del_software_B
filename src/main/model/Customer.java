package main.model;

import main.Application;

public class Customer extends User {

    public Customer(String username, String password) {
        super(username, password);
        type = UserType.CUSTOMER;
    }

    @Override
    public void runUserMenu(Application app) {
        //todo: useAsFruitore()
    }
}
