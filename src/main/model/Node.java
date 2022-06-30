package main.model;

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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (categorieFiglie.size() != 0) {
            sb.append("\n\nCategorie figlie di " + this.getNome() + ":");
            int i = 1;
            for (Category c : categorieFiglie) {
                sb.append("\n\n" + (i++) + ")");
                sb.append(c.toString());
            }
        }
        return sb.toString();
    }
}
