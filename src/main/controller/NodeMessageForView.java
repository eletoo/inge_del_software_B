package main.controller;

import main.model.Category;

import java.util.List;

public class NodeMessageForView implements Message {

    public CategoryLongMessageForView getMsg() {
        return msg;
    }

    public List<Category> getCategorieFiglie() {
        return categorieFiglie;
    }

    public String getNome() {
        return this.nome;
    }

    private CategoryLongMessageForView msg;
    private List<Category> categorieFiglie;

    private String nome;

    public NodeMessageForView(CategoryLongMessageForView msg, List<Category> categorieFiglie, String nome) {
        this.msg = msg;
        this.categorieFiglie = categorieFiglie;
        this.nome = nome;
    }
}
