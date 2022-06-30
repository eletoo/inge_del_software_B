package main.model;

import main.Application;
import main.controller.Controller;

import java.io.IOException;

public class Configurator extends User {

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
        //todo: useAsConfiguratore
        boolean end = false;
        String choice = "0";
        do {
            //carica i dati salvati in precedenza
            Controller.prepareStructures(app);

            choice = view.selectConfiguratoreAction();
            switch (choice) {
                case "1": {
                    //crea una nuova gerarchia
                    app.createNewHierarchy(view);
                }
                break;
                case "2": {
                    //visualizza gerarchie
                    for (String r : app.getHierarchies().keySet()) {
                        System.out.println(app.getHierarchy(r).toString());
                        System.out.println(app.getHierarchy(r).getRoot().toString());
                    }
                }
                break;
                case "3": {
                    //salva dati
                    app.saveData();
                    app.saveInfo();
                    app.saveOfferte();
                    app.saveExchanges();
                    view.interactionMessage(View.InteractionMessage.SAVED_CORRECTLY);
                }
                break;
                case "4": {
                    //configura informazioni di scambio
                    if (app.getInformazioni() == null) {
                        app.setInfoScambio(new InfoScambio(app, view));
                    } else {
                        view.interactionMessage(View.InteractionMessage.CURRENT_INFO);
                        System.out.println(app.getInformazioni().toString());
                        if (view.yesOrNoQuestion("\nSovrascrivere le informazioni di scambio presenti (N.B. La piazza non è modificabile)? [Y/N]")) {
                            app.setInfoScambio(new InfoScambio(app, view));
                        }
                    }
                    app.saveInfo();//se non modifico le informazioni di scambio e conf.json è corrotto/incompleto qui viene
                    //sovrascritto con le informazioni correnti complete e non modificate
                    view.interactionMessage(View.InteractionMessage.SAVED_CORRECTLY);
                }
                break;
                case "5": {
                    //mostra offerte per categoria
                    Offer.viewOffersByCategory(app, view);
                }
                break;
                case "6": {
                    //visualizza offerte in scambio di una categoria foglia
                    Offer.viewOffers(app, view, Offer.StatoOfferta.IN_SCAMBIO);
                }
                break;
                case "7": {
                    //visualizza offerte chiuse di una categoria foglia
                    Offer.viewOffers(app, view, Offer.StatoOfferta.CHIUSA);
                }
                break;
                case "8": {
                    //configura le gerarchie da file
                    app.importHierarchiesFromFile(this.view);
                }
                break;
                case "9": {
                    //configura le impostazioni di scambio da file
                    app.importInfoFromFile(this.view);
                }
                break;
                case "10": {
                    //esci
                    end = true;
                    view.interactionMessage(View.InteractionMessage.EXIT_MESSAGE);
                }
                break;
                default:
                    view.errorMessage(View.ErrorMessage.E_ILLICIT_CHOICE);
            }
        } while (!end);
    }
    }
}
