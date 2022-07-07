package main.model;

import main.controller.Controller;
import main.controller.GenericMessage;
import main.controller.YesOrNoMessage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Category implements Serializable {

    private String nome;
    private String descrizione;
    private Map<String, NativeField> campiNativi;

    /**
     * Costruttore, inizializza la mappa che tiene traccia dei campi nativi
     *
     * @param _nome        nome della categoria
     * @param _descrizione descrizione della categoria
     */
    public Category(String _nome, String _descrizione) {
        this.nome = _nome;
        this.descrizione = _descrizione;
        campiNativi = new HashMap<>();
    }

    /**
     * @return descrizione della categoria
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @return nome della categoria
     */
    public String getNome() {
        return nome;
    }


    /**
     * @return mappa dei campi nativi
     */
    public Map<String, NativeField> getNativeFields() {
        return campiNativi;
    }

    /**
     * @param descrizione descrizione da dare alla categoria
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @param nome nome da dare alla categoria
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param campi campi da assegnare alla categoria
     */
    public void setNativeFields(Map<String, NativeField> campi) {
        this.campiNativi = campi;
    }

    /**
     * Override del metodo toString() per consentire la stampa del contenuto della categoria.
     *
     * @return stringa contenente nome, descrizione, elenco dei campi e obbligatorieta' di ciascuno di essi
     */
    public String getCategoryDefinition() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome);
        sb.append("\nDescrizione: ").append(this.descrizione);
        sb.append("\nCampi nativi:");
        for (String n : campiNativi.keySet()) {
            sb.append("\n-> " + n);
            sb.append(campiNativi.get(n).isObbligatorio() ? " (Obbligatorio)" : " (Falcotativo)");
        }
        return sb.toString();
    }

    /**
     * Ritorna true se e solo se il nome passato e' presente all'interno della struttura della gerarchia.
     *
     * @param name nome
     * @return true se il nome è già stato utilizzato
     */
    public boolean isNameTaken(String name) {
        return isNameTaken(this, name);
    }

    /**
     * Funzione ricorsiva che computa per ogni livello se il nome e' stato utilizzato o meno.
     *
     * @param c    categoria in esame
     * @param name nome
     * @return true se name e' il nome della categoria o di uno dei suoi figli
     */
    private static boolean isNameTaken(@NotNull Category c, @NotNull String name) {
        if (name.equals(c.nome))
            return true;

        if (c instanceof Node)
            for (var child : ((Node) c).getCategorieFiglie())
                if (isNameTaken(child, name))
                    return true;
        return false;
    }

    /**
     * Funzione che verifica se un nome e' presente esattamente una volta all'interno della gerarchia
     *
     * @param root categoria radice del ramo di cui analizzare il numero di occorrenze di un nome
     * @return true se il nome e' presente una sola volta nella gerarchia
     */
    public boolean isNameTakenOnlyOnce(@NotNull Category root) {
        return this.countNameOccurences(0, root) == 1;
    }

    /**
     * Funzione che computa il numero di occorrenze di un nome di categoria all'interno di una gerarchia
     *
     * @param count conteggio delle occorrenze
     * @param c     categoria radice del ramo della gerarchia in cui cercare il nome
     * @return numero di occorrenze del nome
     */
    @Contract(pure = true)
    private int countNameOccurences(int count, @NotNull Category c) {
        if (this.nome.equals(c.nome))
            count++;

        if (c instanceof Node)
            for (var child : ((Node) c).getCategorieFiglie())
                count = countNameOccurences(count, child);

        return count;
    }

    /**
     * @return true se le condizioni di struttura sono rispettate (se ha dei figli devono essercene almeno due)
     */
    public boolean isStructureValid() {
        return isStructureValid(this);
    }

    /**
     * Funzione ricorsiva per la validazione della struttura di una categoria.
     *
     * @param c categoria di cui validare la struttura
     * @return true se e' una foglia o se e' un nodo con almeno due figli con struttura valida
     */
    private static boolean isStructureValid(Category c) {
        if (c instanceof Leaf)
            return true;

        if (c instanceof Node) {
            if (((Node) c).getCategorieFiglie().size() < 2)
                return false;
            for (var child : ((Node) c).getCategorieFiglie())
                if (!isStructureValid(child))
                    return false;
        }
        return true;
    }

    /**
     * Restituisce i campi nativi da assegnare alla categoria radice dotandoli di opportuni valori che ne indicano
     * la compilazione obbligatoria o meno.
     *
     * @return campi da assegnare alla categoria radice
     */
    public static @NotNull Map<String, NativeField> generateRootNativeFields() {
        NativeField statoConservazione = new NativeField(true, NativeField.Tipo.STRING);
        NativeField descrizioneLibera = new NativeField(false, NativeField.Tipo.STRING);
        Map<String, NativeField> campi = new HashMap<>();
        campi.put("Stato Conservazione", statoConservazione);
        campi.put("Descrizione Libera", descrizioneLibera);
        return campi;
    }

    /**
     * Override del metodo equals()
     *
     * @param o oggetto con cui effettuare il confronto
     * @return true se gli oggetti sono uguali, cioè hanno stesso nome e descrizione
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category categoria = (Category) o;
        return Objects.equals(nome, categoria.nome) && Objects.equals(descrizione, categoria.descrizione);
    }

    /**
     * Override del metodo hashCode()
     *
     * @return hashCode dell'oggetto
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, descrizione, campiNativi);
    }

    /**
     * @return stringa contenente una breve descrizione della categoria (solo nome)
     */
    public String printShortDescription() {
        return "Categoria " + this.nome;
    }
}

