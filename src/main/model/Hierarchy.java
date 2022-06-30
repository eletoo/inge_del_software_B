package main.model;

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

    /**
     * @return stringa descrittiva della gerarchia
     */
    public String toString() {
        return "\nGerarchia: " + root.getNome() + "\nDescrizione: " + root.getDescrizione();
    }


}
