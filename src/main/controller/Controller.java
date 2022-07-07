package main.controller;

import main.model.Application;
import main.controller.actions.Exit;
import main.controller.structures.*;
import main.model.*;
import main.view.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Controller {

    //parla con la view
    private Application app;

    public View getView() {
        return view;
    }

    private View view;

    private RegistrationHandler rh;

    public Controller(Application app, View view) {
        this.app = app;
        this.view = view;
        this.rh = new RegistrationHandler(app);
    }

    public void run() throws IOException {
        ActionSelection sel = new ActionSelection();
        List<Selectable> selectable = Context.initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(this.view, selectable, Selectable::getActionName);
            selected.runAction(this);
        } while (!(selected instanceof Exit));
    }

    public void signalToView(String message) {
        // this.view.notify(message);
        MessagePrinter.printText(message);
    }

    public <T> void signalListToView(@NotNull List<T> toPrint, Function<T, String> toApply) {
        toPrint.forEach(e -> signalToView(toApply == null ? e.toString() : toApply.apply(e)));
    }

    public String askStringFromView(Message message) {
        return (new StringReaderClass()).in(message);
    }

    public String askLineFromView(Message message) {
        return (new StringReaderClass()).inLine(message);
    }

    public String askPotentiallyEmptyStringFromView(Message message) {
        return (new StringReaderClass()).inPotentiallyEmptyLine(message); //todo check
    }

    public int askIntFromView(Message message) {
        return (new IntegerReader().in(message));
    }

    public boolean askBooleanFromView(Message message) {
        String ans;
        do {
            ans = askStringFromView(message);
        } while (!ans.equalsIgnoreCase("y") && !ans.equalsIgnoreCase("n"));

        return ans.equalsIgnoreCase("y");
    }

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

    public boolean isFirstAccess() throws IOException {
        app.getUserDataStore().load();
        if (app.getUserDataStore().isEmpty()) {
            this.registerUser(UserType.CONFIGURATOR).onLogin(this).runUserMenu(this);
            return true;
        }
        return false;
    }

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

    public User onConfiguratorFirstLogin(Configurator u) {
        this.signalToView(GenericMessage.CUSTOMIZE_CREDENTIALS.getMessage());

        String username;
        do {
            username = this.askStringFromView(GenericMessage.CUSTOMIZE_USERNAME);
            if (this.getApp().getUserDataStore().isUsernameTaken(username)) {
                this.signalToView(ErrorMessage.E_CREDENTIALS_ERROR.getMessage());
                continue;
            }

            String password = this.askStringFromView(GenericMessage.CUSTOMIZE_PW);

            this.getApp().getUserDataStore().updateUser(u.getUsername(), username, password);
            this.getApp().getUserDataStore().save();
            return this.getApp().getUserDataStore().getUser(username);
        } while (true);
    }

    private void manageExchange(@NotNull Exchange e, Customer c) throws IOException {
        if (e.getOwnerMessage().getMessage() == null && e.getCounterMessage() == null) {//A propone lo scambio, B lo riceve per la prima volta, messageA è ancora null
            e.suggestMeeting(this, this.app, c);//todo: potrebbe essere da rimuovere la seconda condizione in AND
            app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.IN_SCAMBIO);
            app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.IN_SCAMBIO);
            app.save();
            return;
        }

        //altrimenti è già stato proposto almeno uno scambio
        this.signalToView(e.getLastMessageByCounterpart(c).getMessage());//todo: potenziale errore in getLastMessageByCounterpart

        if (this.askBooleanFromView(YesOrNoMessage.ACCEPT_MEETING)) {
            app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.CHIUSA);
            app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.CHIUSA);
            this.signalToView(GenericMessage.CLOSED_OFFER.getMessage());
            app.getExchangesStore().removeExchange(e);
            app.save();
            return;
        }

        e.suggestMeeting(this, this.app, c);
        app.getOffersStore().getOffer(e.getSelectedOffer()).setState(OfferState.IN_SCAMBIO);
        app.getOffersStore().getOffer(e.getOwnOffer()).setState(OfferState.IN_SCAMBIO);
        app.save();
    }

    private void manageExchange(Customer c, String noExchanges, String existingExchanges, Message selectExchange, @NotNull List<Exchange> userExchanges) throws IOException {
        while (!userExchanges.isEmpty()) {
            Exchange toAccept;
            this.signalToView(existingExchanges);
            this.signalListToView(userExchanges, Exchange::getExchangeDescription);

            if (this.askBooleanFromView(selectExchange)) {
                toAccept = this.view.choose(userExchanges, Exchange::getExchangeDescription);

                manageExchange(toAccept, c);

                userExchanges.remove(toAccept);
                app.save();
            } else {
                break;
            }
        }
        this.signalToView(noExchanges);
    }

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
                GenericMessage.NO_NEW_OFFERS.getMessage(),
                GenericMessage.NEW_OFFERS.getMessage(),
                YesOrNoMessage.SELECT_EXCHANGE,
                valid_exchanges);

        manageExchange(
                customer,
                GenericMessage.NO_PAST_OFFERS.getMessage(),
                GenericMessage.PAST_OFFERS.getMessage(),
                YesOrNoMessage.SELECT_EXCHANGE,
                past_exchanges);

        for (Exchange s : invalid_exchanges) {
            this.app.getOffersStore().getOffer(s.getOwnOffer()).setState(OfferState.APERTA);
            this.app.getOffersStore().getOffer(s.getSelectedOffer()).setState(OfferState.APERTA);
            this.app.getExchangesStore().removeExchange(s);
        }

        this.app.getExchangesStore().save();
        return customer;
    }
}
