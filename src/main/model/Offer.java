package main.model;

import main.controller.OfferMessageForView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Offer implements Serializable {
    private String name;
    private Leaf category;
    private Customer owner;
    private OfferState state;
    private Map<String, Object> fieldsValues = new HashMap<>();

    /**
     * Costruttore
     *
     * @param name     nome offerta
     * @param category categoria a cui appartiene l'offerta
     * @param owner    utente creatore dell'offerta
     * @param stato    stato
     */
    public Offer(String name, Leaf category, Customer owner, OfferState stato) {
        this.name = name;
        this.category = category;
        this.owner = owner;
        this.state = stato;
    }

    /**
     * @return stato dell'offerta
     */
    public OfferState getState() {
        return state;
    }

    /**
     * @param stato stato offerta
     */
    public void setState(OfferState stato) {
        this.state = stato;
    }

    /**
     * @return nome offerta
     */
    public String getName() {
        return name;
    }

    /**
     * @return categoria foglia a cui appartiene l'offerta
     */
    public Leaf getCategory() {
        return category;
    }

    /**
     * @return proprietario dell'offerta
     */
    public Customer getOwner() {
        return owner;
    }

    /**
     * @return valore dei campi dell'offerta
     */
    public Map<String, Object> getFieldsValues() {
        return fieldsValues;
    }

    /**
     * @return stringa contenente le informazioni relative a un'offerta
     */
    public OfferMessageForView getOfferInfos() {
        return new OfferMessageForView(this.name, this.category, this.owner, this.state, this.fieldsValues);
    }

    /**
     * @return true se un'offerta non e' stata selezionata per uno scambio (i.e. e' un'offerta aperta)
     */
    public boolean isAvailableOffer() {
        return this.state == OfferState.APERTA;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offerta = (Offer) o;
        return Objects.equals(name, offerta.name)
                && Objects.equals(category, offerta.category)
                && Objects.equals(owner, offerta.owner)
                && Objects.equals(fieldsValues, offerta.fieldsValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, owner, state, fieldsValues);
    }


}
