package main.model;

import java.util.*;

public class UserDataStore implements Loadable{

    private Map<String, User> userMap;

    public UserDataStore(){
        this.userMap = new HashMap<>();
    }

    @Override
    public void load() {
//todo
    }

    public boolean isEmpty(){
        return this.userMap.isEmpty();
    }

}
