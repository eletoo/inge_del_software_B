package main.model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class HierarchiesStore implements Loadable, Saveable, Serializable {
    private Map<String, Hierarchy> hierarchies;

    public HierarchiesStore(){
        this.hierarchies = new HashMap<>();
    }

    public Map<String, Hierarchy> getHierarchies(){
        return this.hierarchies;
    }

    public void setHierarchies(Map<String, Hierarchy> hierarchies){
        this.hierarchies = hierarchies;
    }


    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var gf = new File(System.getProperty("user.dir") + "/db/gerarchie.dat");
        if (gf.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gf));
            try {
                this.setHierarchies((Map<String, Hierarchy>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setHierarchies(new HashMap<>());

            }
        } else {
            this.setHierarchies(new HashMap<>());
        }
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/db/gerarchie.dat"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getHierarchies());
        objectOutputStream.close();
        for (Hierarchy h : this.getHierarchies().values()) {
            this.printHierarchyOnFile(h);
        }
    }

    //todo: aggiungere un metodo printOnFile e importFromFile all'interfaccia loadable e saveable
    //implementarli in ogni <Something>Store per importare i dati da json o salvare i dati su json
    //aggiungerli dove opportuno per gestire la lettura/scrittura dei dati
    //chiamare printOnFile nel metodo save()

}
