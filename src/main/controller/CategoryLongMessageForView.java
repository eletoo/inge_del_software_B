package main.controller;

import main.model.NativeField;

import java.util.Map;

public class CategoryLongMessageForView implements Message{

    private String nome;
    private String descrizione;
    private Map<String, NativeField> campinativi;

    public CategoryLongMessageForView(String nome, String descrizione, Map<String, NativeField> campinativi) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.campinativi = campinativi;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Map<String, NativeField> getCampinativi() {
        return campinativi;
    }
}
