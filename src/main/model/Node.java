package main.model;

import main.controller.*;

import java.util.ArrayList;
import java.util.List;

public class Node extends Category {
    private List<Category> categorieFiglie;

    /**
     * Costruttore: inizializza la lista delle categorie figlie
     *
     * @param _nome        nome del nodo
     * @param _descrizione descrizione del nodo
     */
    public Node(String _nome, String _descrizione) {
        super(_nome, _descrizione);
        categorieFiglie = new ArrayList<>();
    }

    /**
     * @return lista delle categorie figlie
     */
    public List<Category> getCategorieFiglie() {
        return categorieFiglie;
    }

    /**
     * Rimuove una categoria dalla lista delle figlie
     *
     * @param child categoria figlia da rimuovere
     */
    public void removeChild(Category child) {
        this.categorieFiglie.remove(child);
    }

    /**
     * Aggiunge una categoria alla lista delle figlie
     *
     * @param child categoria figlia da aggiungere
     */
    public void addChild(Category child) {
        this.categorieFiglie.add(child);
    }

    /**
     * @return stringa descrittiva della categoria nodo
     */
    public Message getCategoryDefinition() {
        return new NodeMessageForView((CategoryLongMessageForView) super.getCategoryDefinition(), this.categorieFiglie, this.getNome());
    }

}
