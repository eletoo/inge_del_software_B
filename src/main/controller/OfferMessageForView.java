package main.controller;

import main.model.Customer;
import main.model.Leaf;
import main.model.OfferState;

import java.util.Map;

public class OfferMessageForView implements Message {
    private String name;
    private Leaf category;
    private Customer owner;
    private OfferState state;
    private Map<String, Object> fieldsValues;

    public OfferState getState() {
        return state;
    }

    public OfferMessageForView(String name, Leaf category, Customer owner, OfferState state, Map<String, Object> fieldsValues) {
        this.name = name;
        this.category = category;
        this.owner = owner;
        this.state = state;
        this.fieldsValues = fieldsValues;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getFieldsValues() {
        return fieldsValues;
    }

    public Customer getOwner() {
        return owner;
    }

    public Leaf getCategory() {
        return category;
    }
}
