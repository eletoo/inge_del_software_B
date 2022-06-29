package main.model;

public class Configurator extends User {

    /**
     * Costruttore: salva la password dopo l'hashing
     *
     * @param _username username
     * @param _password password in chiaro
     */
    public Configurator(String _username, String _password) {
        super(_username, _password);
        privileged = true;
        type = UserType.CONFIGURATOR;
    }

    @Override
    public void runUserMenu() {
        //todo: useAsConfiguratore
    }
}
