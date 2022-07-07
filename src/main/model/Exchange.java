package main.model;

import main.controller.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exchange implements Serializable, ListSelect {

    private Offer ownOffer;
    private Offer selectedOffer;
    private ExchangeMessage messageA;
    private ExchangeMessage messageB;
    private LocalDateTime dateTime;

    /**
     * Costruttore
     *
     * @param own      offerta accoppiata
     * @param selected offerta selezionata
     */
    public Exchange(@NotNull Offer own, @NotNull Offer selected) {
        this.ownOffer = own;
        this.selectedOffer = selected;

        //todo will it work?
//        app.getOffersStore().getOffer(this.ownOffer).setState(OfferState.ACCOPPIATA);
//        app.getOffersStore().getOffer(this.selectedOffer).setState(OfferState.SELEZIONATA);
        this.ownOffer.setState(OfferState.ACCOPPIATA);
        this.selectedOffer.setState(OfferState.SELEZIONATA);

        this.dateTime = LocalDateTime.now();
        this.messageA = new ExchangeMessage(null, own.getOwner());
        this.messageB = new ExchangeMessage(null, selected.getOwner());

    }


    /**
     * Fa selezionare le due offerte da scambiare, gestendo i casi in cui l'elenco di offerte di uno dei due autori sia vuoto
     *
     * @param app    applicazione
     * @param author autore della proposta di scambio
     * @return nuovo oggetto scambio
     */


    /**
     * @param app applicazione
     * @return true se lo scambio a' ancora valido, cioe' non e' ancora stata superata la scadenza
     */
    public boolean isValidExchange(@NotNull Application app) {
        //riga di test per verificare il funzionamento corretto del timer
        //return LocalDateTime.now().isBefore(this.dateTime.plusSeconds(app.getInformazioni().getScadenza()))
        //      && (this.ownOffer.getStato() != Offerta.StatoOfferta.CHIUSA && this.selectedOffer.getStato() != Offerta.StatoOfferta.CHIUSA);

        return LocalDateTime.now().isBefore(this.dateTime.plusDays(getInformation(app).getDeadline()))
                && (this.ownOffer.getState() != OfferState.CHIUSA && this.selectedOffer.getState() != OfferState.CHIUSA);
    }


    private Information getInformation(@NotNull Application app) {
        return app.getInformationStore().getInformation();
    }

    public ExchangeMessage getMessageA() {
        return messageA;
    }

    public ExchangeMessage getMessageB() {
        return messageB;
    }

    /**
     * Chiede all'utente le informazioni per un appuntamento per lo scambio
     *
     * @param app  applicazione
     * @param f    autore della proposta di appuntamento
     */
    public void suggestMeeting(@NotNull Application app, Customer f) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nInformazioni appuntamento per lo scambio:");
        sb.append("\nLuogo: " + choose(this.getInformation(app).getAddresses(), null));
        sb.append("\nGiorno: " + choose(this.getInformation(app).getDays(), Day::getDay));

        List<Time> orari = new LinkedList<>();
        this.getInformation(app).getTimeIntervals().stream().forEach(e -> orari.addAll(e.getSingoliOrari()));
        sb.append("\nOrario: " + choose(orari, null));

        if (this.ownOffer.getOwner().equals(f))
            this.messageB.setMessage(sb.toString());
        else
            this.messageA.setMessage(sb.toString());

        this.dateTime = LocalDateTime.now();
    }

    /**
     * @return destinatario di un'offerta di scambio
     */
    public Customer getDest() {
        return this.selectedOffer.getOwner();
    }

    /**
     * @return autore di un'offerta di scambio
     */
    public Customer getAuthor() {
        return this.ownOffer.getOwner();
    }

    /**
     * Gestisce le offerte di scambio di cui l'utente e' destinatario
     *
     * @param app  applicazione
     * @param dest utente destinatario
     * @throws IOException eccezione
     */
    public void manageExchanges(@NotNull Application app, Customer dest) throws IOException {
        this.manage(app,
                GenericMessage.NO_NEW_OFFERS.getMessage(),
                GenericMessage.NEW_OFFERS.getMessage(),
                YesOrNoMessage.SELECT_EXCHANGE,
                dest,
                Exchange::getDest);
    }

    /**
     * Gestisce le offerte di scambio di cui l'utente e' autore
     *
     * @param app    applicazione
     * @param author utente autore
     * @throws IOException eccezione
     */
    public void managePastExchanges(@NotNull Application app, Customer author) throws IOException {
        this.manage(app,
                GenericMessage.NO_PAST_OFFERS.getMessage(),
                GenericMessage.PAST_OFFERS.getMessage(),
                YesOrNoMessage.SELECT_EXCHANGE,
                author,
                Exchange::getAuthor);
    }

    /**
     * Gestisce le offerte di scambio di un utente
     *
     * @param app               applicazione
     * @param noExchanges       messaggio che comunica che non ci sono offerte di scambio
     * @param existingExchanges messaggio che comunica che ci sono offerte di scambio
     * @param selectExchange    richiesta di selezione di uno scambio da una lista
     * @param f                 utente fruitore
     * @param predicate         predicato da applicare a un oggetto Scambio per selezionare gli scambi con un determinato autore o destinatario
     * @throws IOException eccezione
     */
    private void manage(@NotNull Application app, String noExchanges, String existingExchanges, Message selectExchange, Customer f, Function<Exchange, Customer> predicate) throws IOException {
        //gestione scambi validi
//        List<Exchange> userExchanges;
//        if (getExchanges(app) == null)
//            userExchanges = new ArrayList<>();
//        else
//            userExchanges = app.getExchangesStore()
//                    .getValidExchanges(this.getExchanges(app), app)
//                    .stream()
//                    .filter(e -> predicate.apply(e).equals(f))
//                    .collect(Collectors.toList());
//
//        if (userExchanges.isEmpty())
//            Controller.signalToView(noExchanges);
//
//        while (!userExchanges.isEmpty()) {
//            Exchange toAccept;
//            Controller.signalToView(existingExchanges);
//            Controller.signalListToView(userExchanges, null);
//
//            if (Controller.askBooleanFromView(selectExchange)) {
//                toAccept = choose(userExchanges, null);
//                toAccept.manageExchange(app, f);
//                userExchanges.remove(toAccept);
//                app.getExchangesStore().save();
//            } else {
//                break;
//            }
//        }
//        //gestione scambi invalidi
//        this.manageInvalidExchanges(app);
    }

    private List<Exchange> getExchanges(@NotNull Application app) {
        return app.getExchangesStore().getExchanges();
    }

    /**
     * Gestisce le offerte di scambio invalide, cioe' scadute
     *
     * @param app
     */
    private void manageInvalidExchanges(@NotNull Application app) throws IOException {
        if (this.getExchanges(app) == null)
            return;

        var scambi = this.getExchanges(app)
                .stream()
                .filter(e -> !e.isValidExchange(app))
                .collect(Collectors.toList());

        for (Exchange s : scambi) {
            app.getOffersStore().getOffer(s.ownOffer).setState(OfferState.APERTA);
            app.getOffersStore().getOffer(s.selectedOffer).setState(OfferState.APERTA);

            app.getExchangesStore().removeExchange(s);
        }

        app.getExchangesStore().save();
    }

    public Offer getSelectedOffer() {
        return selectedOffer;
    }

    public Offer getOwnOffer() {
        return ownOffer;
    }

    /**
     * @return stringa contenente una breve descrizione dello scambio da effettuare
     */
    public String toString() {
        return this.selectedOffer.getName() + " <--> " + this.ownOffer.getName();
    }//TODO: TOSTRING

    /**
     * @return stringa contenente una breve descrizione dello scambio e l'ultimo messaggio a esso relativo introdotto
     * dall'utente controparte nello scambio
     */
    private @NotNull ExchangeMessage getLastMsg(User f) {
        if (this.messageB.getAuthor().equals(f))
            return this.messageB;

//        return (this.messageA.getMessage() == null) ? "--" : this + ": " + this.messageA.getMessage();
        return this.messageA;
    }

    public ExchangeMessage getLastMessageByCounterpart(User user) {
        return this.getLastMsg(user);
    }

}
