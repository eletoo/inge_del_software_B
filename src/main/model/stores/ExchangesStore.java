package main.model.stores;

import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;
import main.model.Exchange;
import main.model.Loadable;
import main.model.Saveable;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * contiene gli scambi
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
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

    /**
     * @param exchange scambio da aggiungere
     */
    public void addExchange(Exchange exchange) {
        this.exchanges.add(exchange);
    }

    /**
     * @param exchange scambio da rimuovere
     */
    public void removeExchange(Exchange exchange) {
        this.exchanges.remove(exchange);
    }

    /**
     * carica gli scambi salvati
     *
     * @throws IOException eccezione
     */
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

    /**
     * lancia eccezione perché gli scambi non sono caricabili da file
     *
     * @throws IOException eccezione
     */
    @Override
    public void loadFromFile() throws IOException {
        throw new NonLoadableFromFileException();
    }

    /**
     * salva gli scambi
     *
     * @throws IOException eccezione
     */
    @Override
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(EXCHANGES_DIR);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getExchanges());
        objectOutputStream.close();
    }

    /**
     * lancia eccezione perché gli scambi non vengono salvati su file
     *
     * @param s oggetto da salvare
     */
    @Override
    public void saveOnFile(Serializable s) {
        throw new NonSaveableOnFileException();
    }
}
