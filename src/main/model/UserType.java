package main.model;

public enum UserType {
    CONFIGURATOR("Configuratore"),
    CUSTOMER("Fruitore");

    private String message;

    UserType(String msg) {
        this.message = msg;
    }

    public String getUserType() {
        return this.message;
    }


}
