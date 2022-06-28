package main.model;

public enum UserType {
    CONFIGURATOR("Configuratore"),
    CUSTOMER("Fruitore");

    UserType(String msg){
        this.message = msg;
    }

    private String message;

    public String getUserType() {
        return this.message;
    }


}
