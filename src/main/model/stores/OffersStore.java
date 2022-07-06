package main.model.stores;

import main.Application;
import main.controller.*;
import main.exceptions.NonLoadableFromFileException;
import main.exceptions.NonSaveableOnFileException;
import main.exceptions.RequiredConstraintFailureException;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class OffersStore implements Loadable, Saveable, Serializable, ListSelect {
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

    public List<Offer> getOffers(Customer customer) {
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

    public void viewOffersByCategory(@NotNull Application app) {
        if (app.getHierarchiesStore().getHierarchies().size() == 0) {
            Controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        Controller.signalListToView(
                this.getOffers(chooseLeaf(GenericMessage.SELECT_CATEGORY.getMessage(), app))
                        .stream()
                        .filter(e -> e.getState() == OfferState.APERTA)
                        .collect(Collectors.toList()), null
        );
    }

    /**
     * Permette di creare un'offerta indicando la categoria foglia di appartenenza, il nome dell'offerta e il valore dei
     * campi nativi (obbligatori e facoltativi)
     *
     * @param app      applicazione
     * @param fruitore utente fruitore
     * @throws IOException eccezione I/O
     */
    public void createOffer(@NotNull Application app, Customer fruitore) throws IOException {
        if (app.getHierarchiesStore().getHierarchies().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        var cat = chooseLeaf(GenericMessage.CHOOSE_CATEGORY_TO_PUBLISH.getMessage(), app);
        var offer = new Offer(Controller.askStringFromView(GenericMessage.NAME), cat, fruitore, OfferState.APERTA);

        for (var field : cat.getNativeFields().entrySet()) {
            inputField(offer, field);
        }

        try {
            this.addOffer(offer);
        } catch (RequiredConstraintFailureException e) {
            e.printStackTrace(); //should not happen
        }

        this.save();
    }

    private void addOffer(Offer offer) {
        this.offers.add(offer);
    }

    /**
     * Permette l'inserimento del valore di un campo
     *
     * @param offer offerta
     * @param field campo da compilare
     */
    private static void inputField(@NotNull Offer offer, Map.@NotNull Entry<String, NativeField> field) {
        Controller.signalToView("Valore per "
                + field.getKey()
                + (field.getValue().isObbligatorio() ? " (Obbligatorio) " : "(Opzionale)")
                + "-- SOLO per campi opzionali: Enter per saltare");

        offer.getFieldsValues()
                .put(
                        field.getKey(),
                        field.getValue().getType().deserialize(
                                Controller.askStringFromView(null)
                        )
                );
    }

    /**
     * Mostra la lista di offerte personali relative a un utente
     *
     * @param fruitore utente di cui visualizzare le offerte
     */
    public void viewPersonalOffers(Customer fruitore) {
        Controller.signalListToView(this.getOffers(fruitore), null);
    }

    /**
     * Visualizza le offerte di una categoria foglia specificata dall'utente
     *
     * @param app applicazione
     * @param s   stato delle offerte da visualizzare
     */
    public void viewOffers(@NotNull Application app, OfferState s) {
        if (app.getHierarchiesStore().getHierarchies().size() == 0) {
            Controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        Leaf leaf = chooseLeaf(GenericMessage.SELECT_CATEGORY.getMessage(), app);
        Controller.signalListToView(this
                .getOffers()
                .stream()
                .filter(e -> e.getCategory().equals(leaf) && e.getState().equals(s))
                .collect(Collectors.toList()), null);

    }

    /**
     * Permette all'utente di scegliere una categoria foglia da una gerarchia
     *
     * @param prompt prompt da fornire all'utente
     * @param app    applicazione
     * @return categoria foglia selezionata dall'utente
     */
    private Leaf chooseLeaf(String prompt, @NotNull Application app) {
        Controller.signalToView(prompt);

        List<Leaf> choices = new LinkedList<>();
        var stack = new Stack<Category>();
        for (var gerarchia : app.getHierarchiesStore().getHierarchies().entrySet()) {
            stack.clear();
            stack.push(gerarchia.getValue().getRoot());
            while (!stack.empty()) {
                var c = stack.pop();
                if (c instanceof Leaf)
                    choices.add((Leaf) c);
                else
                    stack.addAll(((Node) c).getCategorieFiglie());
            }
        }
        return (Leaf) choose(choices, Category::printShortDescription);
    }


    /**
     * Permette di selezionare un'offerta da ritirare e modificarne lo stato opportunamente
     *
     * @param app      applicazione
     * @param customer fruitore
     * @throws IOException eccezione I/O
     */
    public void undoOffer(Customer customer) throws IOException {
        var user_offers = this
                .getOffers(customer)
                .stream()
                .filter(e -> e.getState() == OfferState.APERTA)
                .collect(Collectors.toList());
        if (user_offers.isEmpty()) {
            Controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
            return;
        }
        var to_edit = (Offer) this.choose(user_offers, null);
        to_edit.setState(OfferState.RITIRATA);
        this.save();
    }

}
