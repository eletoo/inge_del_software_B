package main.model;

import main.Application;

public class Customer extends User {

    public Customer(String username, String password) {
        super(username, password);
        privileged = false;
        type = UserType.CUSTOMER;
    }


    @Override
    public void runUserMenu() {
        //todo: useAsFruitore()
    }
}
