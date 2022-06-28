package main.model;

public class Configuratore extends User{

    /**
     * Costruttore: salva la password dopo l'hashing
     *
     * @param _username username
     * @param _password password in chiaro
     */
    public Configuratore(String _username, String _password) {
        super(_username, _password);
        privileged = true;
        type = UserType.CONFIGURATOR;
    }


    @Override
    public String getUserType() {
        return UserType.CONFIGURATOR.getMessage();
    }
}
