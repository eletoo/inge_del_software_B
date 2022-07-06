package main.controller;

import main.Application;
import main.controller.actions.Exit;
import main.controller.structures.*;
import main.model.*;
import main.view.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

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

    public void run() throws IOException {
        ActionSelection sel = new ActionSelection();
        List<Selectable> selectable = Context.initiateSelectableList();
        Selectable selected;
        do {
            selected = sel.selectAction(selectable, Selectable::getActionName);
            selected.runAction(app, this);
        } while (!(selected instanceof Exit));
    }

    public static void signalToView(String message) {
        MessagePrinter.printText(message);
    }

    public static <T> void signalListToView(@NotNull List<T> toPrint, Function<T, String> toApply){
        toPrint.forEach(e -> signalToView(toApply == null ? e.toString() : toApply.apply(e)));
    }

    public static String askStringFromView(Message message) {
        return (new StringReaderClass()).in(message);
    }

    public static String askPotentiallyEmptyStringFromView(Message message){
        return (new StringReaderClass()).inPotentiallyEmptyLine(message);
    }

    public static String askLineFromVew(Message message){
        return (new StringReaderClass()).inLine(message);
    }

    public static int askIntFromView(Message message) {
        return (new IntegerReader().in(message));
    }

    public static boolean askBooleanFromView(Message message){
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
            this.registerUser(UserType.CONFIGURATOR).runUserMenu(app, this);
            return true;
        }
        return false;
    }

    public User registerUser(UserType type) throws IOException {
        String username;
        do {
            if(type == UserType.CONFIGURATOR)
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



}
