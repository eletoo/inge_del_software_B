package main.model;

import org.jetbrains.annotations.NotNull;

public class CategoryEntry {

    private Category cat;
    private final Node father;
    private final String displayName;

    /**
     * Costruttore.
     */
    public CategoryEntry(Category cat, Node father, String displayName) {
        this.cat = cat;
        this.father = father;
        this.displayName = displayName;
    }

    /**
     * @return la categoria.
     */
    public Category getCat() {
        return cat;
    }

    /**
     * @return la categoria padre.
     */
    public Node getFather() {
        return father;
    }

    /**
     * @return il nome che contiene il percorso da root alla categoria corrente.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Trasforma una categoria foglia in nodo copiandone i valori.
     *
     * @param c categoria da trasformare in nodo
     * @return categoria trasformata in nodo
     */
    public static @NotNull Node catAsNode(Category c) {
        if (c instanceof Node) return (Node) c;

        Node newcat = new Node(c.getNome(), c.getDescrizione());
        newcat.setNativeFields(c.getNativeFields());
        return newcat;
    }

    /**
     * Rimuove la categoria cat dal padre (se possibile) e la riaggiunge (se possibile) sotto forma di categoria nodo.
     *
     * @return categoria sotto forma di Nodo
     */
    public Node asNode() {
        if (this.father != null)
            this.father.removeChild(this.cat);
        this.cat = catAsNode(this.cat);
        if (this.father != null)
            this.father.addChild(this.cat);
        return (Node) this.cat;
    }
}
