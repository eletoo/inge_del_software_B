package main.model.stores;

import com.google.gson.*;
import main.controller.*;
import main.model.Information;
import main.model.Loadable;
import main.model.LocalPath;
import main.model.Saveable;

import java.io.*;
import java.nio.file.*;

public class InformationStore implements Loadable, Saveable, Serializable {

    private static final String DB_JSON_CONF_FILE = System.getProperty("user.dir") + "/db/conf/conf.json";
    private static final String DB_CONF_DIR = System.getProperty("user.dir") + "/db/conf/";
    private Information info;

    public InformationStore() {
        info = null;
    }

    public Information getInformation() {
        return this.info;
    }

    public void setInformations(Information info) {
        this.info = info;
    }

    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var info = new File(System.getProperty("user.dir") + "/db/info.dat");
        if (info.exists()) {
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(info));
            try {
                this.setInformations((Information) ois2.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setInformations(null);
            }
        } else {

            this.setInformations(null);
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        if (LocalPath.generatePathListForImportFromFile(DB_CONF_DIR).size() > 1) {
            //se c'è un numero di file di configurazione maggiore di 1 segnala un errore
            Controller.signalToView(ErrorMessage.E_WRONG_DIR_CONTENT.getMessage());
            return;
        }

        if (LocalPath.generatePathListForImportFromFile(DB_CONF_DIR).size() == 0) {
            //se non ci sono file di configurazione segnala un errore
            Controller.signalToView(ErrorMessage.E_NO_CONF_FILE.getMessage());
            return;
        }

        Path p = Paths.get(DB_JSON_CONF_FILE);

        if (!p.toFile().exists()) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        Reader reader = Files.newBufferedReader(p);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Information info = gson.fromJson(reader, Information.class);

        if (info == null) {
            Controller.signalToView(ErrorMessage.E_NO_CONF_FILE.getMessage());
            return;
        }

        if (info.getPlace() == null || info.getPlace().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        if (info.getTimeIntervals().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        if (info.getDeadline() <= 0) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        if (info.getDays().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        if (info.getAddresses().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
            return;
        }

        this.setInformations(info);
        Controller.signalToView(GenericMessage.SUCCESSFUL_IMPORT.getMessage());

        this.save();
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "/db/info.dat"));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.getInformation());
        oos.close();
        this.saveOnFile(this.getInformation());
    }

    @Override
    public void saveOnFile(Serializable s) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String file = DB_JSON_CONF_FILE;
        try (FileWriter wr = new FileWriter(file)) {
            wr.write(gson.toJson(this.info));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}