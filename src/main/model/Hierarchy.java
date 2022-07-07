package main.model;

import main.controller.Controller;

import java.io.Serializable;

public class Hierarchy implements Serializable {

    private Category root;

    /**
     * Costruttore.
     */
    public Hierarchy(Category root) {
        this.root = root;
    }

    /**
     * @return categoria radice
     */
    public Category getRoot() {
        return root;
    }


}
