package main.model;

import java.util.*;

public class UserDataStore implements Loadable, Saveable{

    private Map<String, User> userMap;

    public UserDataStore(){
        this.userMap = new HashMap<>();
    }

    @Override
    public void load() {
//todo
    }

    public void save(){
        //todo
    }

    public boolean isEmpty(){
        return this.userMap.isEmpty();
    }

    public boolean isUsernameTaken(String username){
        return userMap.containsKey(username);
    }

    public void addUser(User user){
        this.userMap.put(user.getUsername(), user);
        this.save();
    }

    public void updateUser(String oldname, String newname, String newpw) {
        userMap.get(oldname).changeUsername(newname);
        userMap.get(oldname).changePassword(newpw);
        userMap.put(newname, userMap.get(oldname));
        userMap.remove(oldname);
        this.save();
    }

    public boolean isLoginCorrect(String username, String password) {
        //utente non registrato
        if (!userMap.containsKey(username)) {
            return false;
        }

        return userMap.get(username).authenticate(password);
    }

    public User getUser(String username){
        return this.userMap.get(username);
    }

}
