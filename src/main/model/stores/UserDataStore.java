package main.model.stores;

import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;
import main.model.Loadable;
import main.model.Saveable;
import main.model.User;

import java.io.*;
import java.util.*;

public class UserDataStore implements Loadable, Saveable, Serializable {

    private Map<String, User> userMap;

    public UserDataStore() {
        this.userMap = new HashMap<>();
    }

    @Override
    public void load() throws IOException {
        String currentDir = System.getProperty("user.dir");
        var db = new File(currentDir + "/db");
        assert db.exists() || db.mkdir();

        var uf = new File(currentDir + "/db/users.dat");

        if (uf.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(uf));
            try {
                this.setUserMap((Map<String, User>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setUserMap(new HashMap<>());
            }
        } else
            this.setUserMap(new HashMap<>());
    }

    @Override
    public void loadFromFile() {
        throw new NonLoadableFromFileException();
    }

    @Override
    public void save() {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        try {
            fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/db/users.dat");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userMap);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveOnFile(Serializable s) {
        throw new NonSaveableOnFileException();
    }

    public boolean isEmpty() {
        return this.userMap.isEmpty();
    }

    public boolean isUsernameTaken(String username) {
        return userMap.containsKey(username);
    }

    public void addUser(User user) {
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

    public User getUser(String username) {
        return this.userMap.get(username);
    }

    public void setUserMap(Map<String, User> usermap) {
        this.userMap = usermap;
    }
}
