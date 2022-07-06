package main.model;

import main.Application;
import main.controller.Controller;
import main.controller.ListSelect;
import main.controller.Selectable;
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
    public void runUserMenu(Application app) throws IOException {
        Selectable action = null;
        do {
            Controller.prepareStructures(app);

            action = choose(this.createMenu(), Selectable::getActionName);
            action.runAction(app);

        } while (! (action instanceof Exit));
    }

    private @NotNull List<Selectable> createMenu() {
        List<Selectable> menu = new LinkedList<>();
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
