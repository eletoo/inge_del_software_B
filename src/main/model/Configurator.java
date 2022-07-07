package main.model;

import main.controller.*;
import main.controller.configuratorActions.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Configurator extends User implements ListSelect {

    /**
     * Costruttore: salva la password dopo l'hashing
     *
     * @param _username username
     * @param _password password in chiaro
     */
    public Configurator(String _username, String _password) {
        super(_username, _password);
        type = UserType.CONFIGURATOR;
    }

    @Override
    public User onFirstLogin(Controller controller) {
        controller.signalToView(GenericMessage.CUSTOMIZE_CREDENTIALS.getMessage());

        String username;
        do {
            username = controller.askStringFromView(GenericMessage.CUSTOMIZE_USERNAME);
            if(controller.getApp().getUserDataStore().isUsernameTaken(username)) {
                Controller.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
                continue;
            }

            String password = Controller.askStringFromView(GenericMessage.CUSTOMIZE_PW);

            controller.getApp().getUserDataStore().updateUser(this.getUsername(), username, password);
            controller.getApp().getUserDataStore().save();
            return controller.getApp().getUserDataStore().getUser(username);
        } while (true);
    }

    @Override
    public User onLogin(Controller controller) throws IOException {
        Controller.prepareStructures(controller.getApp());
        return this;
    }

    protected @NotNull List<UserSelectable> getUserMenu() {
        List<UserSelectable> menu = new LinkedList<>();
        menu.add(new HierarchyCreation());
        menu.add(new HierarchyContentPrinter());
        menu.add(new DataSaver());
        menu.add(new InformationConfigurator());
        menu.add(new OpenOffersPrinter());
        menu.add(new ExchangingOffersPrinter());
        menu.add(new ClosedOffersPrinter());
        menu.add(new HierarchyFromFileConfigurator());
        menu.add(new InfoFromFileConfigurator());
        menu.add(new Exit());
        return menu;
    }

}
