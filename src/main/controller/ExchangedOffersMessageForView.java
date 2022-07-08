package main.controller;

import main.model.Offer;

public class ExchangedOffersMessageForView implements Message{

    private Offer own;
    private Offer selected;

    public ExchangedOffersMessageForView(Offer own, Offer selected) {
        this.own = own;
        this.selected = selected;
    }

    public Offer getOwn() {
        return own;
    }

    public Offer getSelected() {
        return selected;
    }

}
