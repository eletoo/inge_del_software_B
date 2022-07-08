package main.controller;

public class CategoryShortMessageForView implements Message {

    private String nome;

    public CategoryShortMessageForView(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
