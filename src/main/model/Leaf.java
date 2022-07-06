package main.model;

public class Leaf extends Category {

    /**
     * Costruttore.
     */
    public Leaf(String _nome, String _descrizione) {
        super(_nome, _descrizione);
    }

    /**
     * @return stringa descrittiva del contenuto della foglia
     */
    public void printCategory() {
        super.printCategory();
    }
}
