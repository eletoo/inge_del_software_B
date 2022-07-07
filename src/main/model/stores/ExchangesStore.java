package main.model.stores;

import main.model.Application;
import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;
import main.model.Exchange;
import main.model.Loadable;
import main.model.Saveable;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangesStore implements Saveable, Loadable, Serializable {
    public static final String EXCHANGES_DIR = System.getProperty("user.dir") + "/db/scambi.dat";

    private List<Exchange> exchanges;

    public ExchangesStore() {
        this.exchanges = new LinkedList<>();
    }

    public List<Exchange> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<Exchange> exchanges) {
        this.exchanges = exchanges;
    }

    public void addExchange(Exchange exchange){
        this.exchanges.add(exchange);
    }

    public void removeExchange(Exchange exchange){
        this.exchanges.remove(exchange);
    }

    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var exchanges = new File(EXCHANGES_DIR);
        if (exchanges.exists()) {
            ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(exchanges));
            try {
                this.setExchanges((List<Exchange>) ois2.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setExchanges(new LinkedList<>());
            }
        } else {
            this.setExchanges(new LinkedList<>());
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        throw new NonLoadableFromFileException();
    }

    public List<Exchange> getValidExchanges(@NotNull List<Exchange> scambi, Application app) {
        return scambi.stream().filter(e -> e.isValidExchange(app)).collect(Collectors.toList());
    }

    @Override
    public void save() throws IOException {
        //this.saveOfferte();
        //todo: se non funziona è colpa del commento sopra
        FileOutputStream fileOutputStream = new FileOutputStream(EXCHANGES_DIR);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getExchanges());
        objectOutputStream.close();
    }

    @Override
    public void saveOnFile(Serializable s) {
        throw new NonSaveableOnFileException();
    }
}
