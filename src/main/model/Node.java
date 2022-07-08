package main.model;

import main.controller.CustomMessage;
import main.controller.Message;

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
        StringBuilder string = new StringBuilder(super.getCategoryDefinition().getMessage());
        if (categorieFiglie.size() != 0) {
            string.append("\n\nCategorie figlie di ").append(this.getNome()).append(":");
            int i = 1;
            for (Category c : categorieFiglie) {
               string.append("\n\n").append(i++).append(") ").append(c.getCategoryDefinition());
            }
        }
        return new CustomMessage(string.toString());
    }

}
