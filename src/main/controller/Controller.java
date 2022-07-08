package main.controller;

import main.controller.actions.Exit;
import main.controller.structures.*;
import main.model.*;
import main.view.ActionSelection;
import main.view.IntegerReader;
import main.view.StringReaderClass;
import main.view.View;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * controller
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class Controller {

    //parla con la view
    private Application app;
    private View view;
    private RegistrationHandler rh;

    public Controller(Application app, View view) {
        this.app = app;
        this.view = view;
        this.rh = new RegistrationHandler(app);
    }

    /**
     * prepara le strutture dati
     *
     * @param app applicazione
     * @throws IOException eccezione I/O
     */
    public static void prepareStructures(Application app) throws IOException {
        StructureLoader s = new DirectoryStructure();
        s.prepareStructure(app);
        s = new InfoStructure();
        s.prepareStructure(app);
        s = new OfferStructure();
        s.prepareStructure(app);
        s = new ExchangeStructure();
        s.prepareStructure(app);
    }

    public View getView() {
        return view;
    }

    /**
     * fa selezionare un'azione del menu principale da eseguire
     *
     * @throws IOException eccezione I/O
     */
    public void run() throws IOException {
        ActionSelection sel = new ActionSelection();
        List<Selectable> selectable = Context.initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(this.view, selectable, Selectable::getActionName);
            selected.runAction(this);
        } while (!(selected instanceof Exit));
    }

    /**
     * segnala un messaggio alla view
     *
     * @param message messaggio
     */
    public void signalToView(Message message) {
        this.view.notify(message);
    }

    /**
     * segnala una lista alla view
     *
     * @param toPrint lista da stampare
     * @param toApply funzione da applicare alla lista
     * @param <T>     tipo della lista
     */
    public <T> void signalListToView(@NotNull List<T> toPrint, Function<T, Message> toApply) {
        this.view.showList(toPrint, toApply);
    }

    /**
     * richiede una stringa alla view
     *
     * @param message messaggio
     * @return stringa
     */
    public String askStringFromView(PrintableMessage message) {
        return (new StringReaderClass()).in(message);
    }

    /**
     * richiede una riga di input alla view
     *
     * @param message messaggio
     * @return riga
     */
    public String askLineFromView(PrintableMessage message) {
        return (new StringReaderClass()).inLine(message);
    }

    /**
     * richiede una riga potenzialmente vuota alla view
     *
     * @param message messaggio
     * @return riga
     */
    public String askPotentiallyEmptyStringFromView(PrintableMessage message) {
        return (new StringReaderClass()).inPotentiallyEmptyLine(message);
    }

    /**
     * richiede un intero alla view
     *
     * @param message messaggio
     * @return intero
     */
    public int askIntFromView(PrintableMessage message) {
        return (new IntegerReader().in(message));
    }

    /**
     * richiede un valore booleano alla view
     *
     * @param message messaggio
     * @return booleano
     */
    public boolean askBooleanFromView(PrintableMessage message) {
        String ans;
        do {
            ans = askStringFromView(message);
        } while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n"));

        return ans.equalsIgnoreCase("y");
    }

    /**
     * se e' il primo accesso all'applicazione registra l'utente come configuratore ed esegue il suo menu
     *
     * @return true se non c'e' ancora nessun utente registrato
     * @throws IOException eccezione I/O
     */
    public boolean isFirstAccess() throws IOException {
        app.getUserDataStore().load();
        if (app.getUserDataStore().isEmpty()) {
            this.registerUser(UserType.CONFIGURATOR).onLogin(this).runUserMenu(this);
            return true;
        }
        return false;
    }

    /**
     * registra un utente in funzione del tipo specificato
     *
     * @param type tipo di profilo utente
     * @return utente
     */
    public User registerUser(UserType type) {
        String username;
        do {
            if (type == UserType.CONFIGURATOR)
                username = RandomItemGenerator.generateRandomString(10);
            else
                username = this.askStringFromView(GenericMessage.USERNAME_REQUEST);
        } while (app.getUserDataStore().isUsernameTaken(username));

        String pw = type == UserType.CONFIGURATOR ?
                RandomItemGenerator.generateRandomPassword(10) :
                this.askStringFromView(GenericMessage.PASSWORD_REQUEST);

        User user = this.rh.registerUser(type, username, pw, this);
        app.getUserDataStore().save();
        return user;
    }

    /**
     * chiede all'utente di selezionare una voce dal menu utente
     *
     * @param userMenu menu utente
     * @param u        utente
     * @throws IOException eccezione I/O
     */
    public void runSelectionMenu(List<UserSelectable> userMenu, User u) throws IOException {
        UserSelectable chosen;
        do {
            chosen = this.view.choose(userMenu, UserSelectable::getActionName);
            chosen.runAction(this, u);
        } while (!(chosen instanceof main.controller.configuratorActions.Exit));
    }

    public Application getApp() {
        return this.app;
    }

    /**
     * esegue il cambio delle credenziali temporanee dell'utente configuratore
     *
     * @param u configuratore
     * @return utente configuratore
     */
    public User onConfiguratorFirstLogin(Configurator u) {
        this.signalToView(GenericMessage.CUSTOMIZE_CREDENTIALS);

        String username;
        do {
            username = this.askStringFromView(GenericMessage.CUSTOMIZE_USERNAME);
            if (this.getApp().getUserDataStore().isUsernameTaken(username)) {
                this.signalToView(ErrorMessage.E_CREDENTIALS_ERROR);
                continue;
            }

            String password = this.askStringFromView(GenericMessage.CUSTOMIZE_PW);

            this.getApp().getUserDataStore().updateUser(u.getUsername(), username, password);
            this.getApp().getUserDataStore().save();
            return this.getApp().getUserDataStore().getUser(username);
        } while (true);
    }

    /**
     * permette di gestire una proposta di scambio dell'utente
     *
     * @param e scambio
     * @param c utente fruitore
     * @throws IOException eccezione I/O
     */
    private void manageExchange(@NotNull Exchange e, Customer c) throws IOException {
        //A propone lo scambio, B lo riceve per la prima volta, le stringhe di messageA e messageB sono ancora null
        if (e.getOwnerMessage().getMessage() == null && e.getCounterMessage().getMessage() == null) {
            e.suggestMeeting(this, this.app, c);
            app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.IN_SCAMBIO);
            app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.IN_SCAMBIO);
            app.save();
            return;
        }

        //altrimenti è già stato proposto almeno un appuntamento
        this.view.printExchangeInfo(e.getLastMessageByCounterpart(c).getMessage());

        if (e.getLastMessageByCounterpart(c).getMessage() != null && this.askBooleanFromView(YesOrNoMessage.ACCEPT_MEETING)) {
            app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.CHIUSA);
            app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.CHIUSA);
            this.signalToView(GenericMessage.CLOSED_OFFER);
            app.getExchangesStore().removeExchange(e);
            app.save();
            return;
        }

        e.suggestMeeting(this, this.app, c);
        app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.IN_SCAMBIO);
        app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.IN_SCAMBIO);
        app.save();
    }

    /**
     * gestisce gli scambi dell'utente facendogli selezionare quale gestire tra quelli disponibili
     *
     * @param c                 utente
     * @param noExchanges       messaggio non ci sono scambi disponibili
     * @param existingExchanges messaggio ci sono scambi disponibili
     * @param selectExchange    messaggio seleziona scambio
     * @param userExchanges     scambi dell'utente
     * @throws IOException eccezione I/O
     */
    private void manageExchange(Customer c, PrintableMessage noExchanges, PrintableMessage existingExchanges, PrintableMessage selectExchange, @NotNull List<Exchange> userExchanges) throws IOException {
        while (!userExchanges.isEmpty()) {
            Exchange toAccept;
            this.signalToView(existingExchanges);
            userExchanges.stream().forEach(e -> this.view.printExchangedOffersDescription(e.getExchangeDescription()));

            if (this.askBooleanFromView(selectExchange)) {
                toAccept = this.view.choose(userExchanges, e -> this.view.getExchangedOffersDescription(e.getExchangeDescription()));

                manageExchange(toAccept, c);

                userExchanges.remove(toAccept);
                app.save();
            } else {
                break;
            }
        }
        this.signalToView(noExchanges);
    }

    /**
     * azioni da compiere all'accesso dell'utente: selezionare eventuali scambi e farglieli gestire, rimuovendo
     * scambi scaduti dall'applicazione
     *
     * @param customer utente
     * @return utente
     * @throws IOException eccezione I/O
     */
    public User onUserLogin(Customer customer) throws IOException {

        var valid_exchanges = this.app.getExchangesStore().getExchanges().stream()
                .filter(e -> e.isValidExchange(this.app))
                .filter(e -> e.getDest().equals(customer))
                .collect(Collectors.toList());

        var invalid_exchanges = this.app.getExchangesStore().getExchanges().stream()
                .filter(e -> !e.isValidExchange(this.app))
                .collect(Collectors.toList());

        var past_exchanges = this.app.getExchangesStore().getExchanges().stream()
                .filter(e -> e.isValidExchange(this.app))
                .filter(e -> e.getAuthor().equals(customer))
                .collect(Collectors.toList());

        manageExchange(
                customer,
                GenericMessage.NO_NEW_OFFERS,
                GenericMessage.NEW_OFFERS,
                YesOrNoMessage.SELECT_EXCHANGE,
                valid_exchanges);

        manageExchange(
                customer,
                GenericMessage.NO_PAST_OFFERS,
                GenericMessage.PAST_OFFERS,
                YesOrNoMessage.SELECT_EXCHANGE,
                past_exchanges);

        for (Exchange s : invalid_exchanges) {
            this.app.getOffersStore().getOffer(s.getOwnOffer()).setState(OfferState.APERTA);
            this.app.getOffersStore().getOffer(s.getSelectedOffer()).setState(OfferState.APERTA);
            this.app.getExchangesStore().removeExchange(s);
        }

        this.app.save();
        return customer;
    }
}
