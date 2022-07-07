package main.model.stores;

import main.model.Application;
import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OffersStore implements Loadable, Saveable, Serializable {
    private List<Offer> offers;

    public OffersStore() {
        offers = new LinkedList<>();
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public List<Offer> getOffers(Leaf categoria) {
        return this.offers.stream().filter(e -> e.getCategory().equals(categoria)).collect(Collectors.toList());
    }

    public List<Offer> getOffers(User customer) {
        assert customer instanceof Customer;
        return this.offers.stream().filter(e -> e.getOwner().equals(customer)).collect(Collectors.toList());
    }

    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var of = new File(System.getProperty("user.dir") + "/db/offerte.dat");
        if (of.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(of));
            try {
                this.setOffers((List<Offer>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setOffers(new LinkedList<>());
            }
        } else {
            this.setOffers(new LinkedList<>());
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        throw new NonLoadableFromFileException();
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(System.getProperty("user.dir") + "/db/offerte.dat"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getOffers());
        objectOutputStream.close();
    }

    @Override
    public void saveOnFile(Serializable s) {
        throw new NonSaveableOnFileException();
    }

    public void addOffer(Offer offer) {
        this.offers.add(offer);
    }


    /**
     * Visualizza le offerte di una categoria foglia specificata dall'utente
     *
     * @param leaf foglia
     * @param s   stato delle offerte da visualizzare
     */
    public List<Offer> getOffers(Leaf leaf, OfferState s) {
        return this
                .getOffers()
                .stream()
                .filter(e -> e.getCategory().equals(leaf) && e.getState().equals(s))
                .collect(Collectors.toList());
    }

    /**
     * Permette all'utente di scegliere una categoria foglia da una gerarchia
     * @param app    applicazione
     * @return categoria foglia selezionata dall'utente
     */
    public List<CategoryEntry> getLeafCategories(@NotNull Application app) {
        List<CategoryEntry> choices = new LinkedList<>();
        var stack = new Stack<CategoryEntry>();
        for (var gerarchia : app.getHierarchiesStore().getHierarchies().entrySet()) {
            stack.clear();
            stack.push(new CategoryEntry(gerarchia.getValue().getRoot(), null, gerarchia.getValue().getRoot().getNome()));
            while (!stack.empty()) {
                var c = stack.pop();
                if (c.getCat() instanceof Leaf)
                    choices.add(c);
                else
                    stack.addAll(((Node) c.getCat()).getCategorieFiglie().stream()
                            .map(e -> new CategoryEntry(e, (Node) c.getCat(), c.getDisplayName() + " > " + e.getNome()))
                            .collect(Collectors.toList())
                    );
            }
        }
        return choices;
    }

    public Offer getOffer(Offer off) {
        if (this.offers.contains(off))
            return this.offers.get(this.offers.indexOf(off));
        throw new RuntimeException("ERROR"); //should not happen
    }

    public List<Offer> getOffers(Customer utente, OfferState stato) {
        return this.offers
                .stream()
                .filter(e -> e.getOwner().equals(utente))
                .filter(e -> e.getState().equals(stato))
                .collect(Collectors.toList());
    }

}
