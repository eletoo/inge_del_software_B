package main.model;

import main.controller.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Exchange implements Serializable {
    private Offer ownOffer;
    private Offer selectedOffer;
    private ExchangeMessage ownerMessage;
    private ExchangeMessage counterMessage;
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

        this.ownOffer.setState(OfferState.ACCOPPIATA);
        this.selectedOffer.setState(OfferState.SELEZIONATA);

        this.dateTime = LocalDateTime.now();
        this.ownerMessage = new ExchangeMessage(null, own.getOwner());
        this.counterMessage = new ExchangeMessage(null, selected.getOwner());

    }

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

    public ExchangeMessage getOwnerMessage() {
        return ownerMessage;
    }

    public ExchangeMessage getCounterMessage() {
        return counterMessage;
    }

    /**
     * Chiede all'utente le informazioni per un appuntamento per lo scambio
     *
     * @param app applicazione
     * @param f   autore della proposta di appuntamento
     */
    public void suggestMeeting(@NotNull Controller controller, @NotNull Application app, Customer f) {
        List<Time> orari = new LinkedList<>();
        this.getInformation(app).getTimeIntervals().stream().forEach(e -> orari.addAll(e.getSingoliOrari()));
        controller.signalToView(GenericMessage.SUGGEST_MEETING);

        ExchangeMessageForView em = new ExchangeMessageForView(
                app.getInformationStore().getInformation().getPlace(),
                controller.getView().choose(GenericMessage.ADDRESS, this.getInformation(app).getAddresses(), null),
                controller.getView().choose(GenericMessage.DAY, this.getInformation(app).getDays(), e -> controller.getView().getDay(new DayMessageForView(e.getDay()))),
                controller.getView().choose(GenericMessage.TIME_RANGE, orari, e -> controller.getView().getTimeDescription(e.getTimeDescription()))
        );

        if (this.selectedOffer.getOwner().equals(f))//se l'offerta selezionata ?? la mia
            this.counterMessage.setMessage(em);//imposto il messaggio B con il mio messaggio
        else
            this.ownerMessage.setMessage(em);//altrimenti io sono il proprietario dell'offerta e imposto il mio msg

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

    public Offer getSelectedOffer() {
        return selectedOffer;
    }

    public Offer getOwnOffer() {
        return ownOffer;
    }

    /**
     * @return oggetto contenente una breve descrizione dello scambio da effettuare
     */
    public ExchangedOffersMessageForView getExchangeDescription() {
        return new ExchangedOffersMessageForView(ownOffer, selectedOffer);
    }

    /**
     * @return oggetto contenente una breve descrizione dello scambio e l'ultimo messaggio a esso relativo introdotto
     * dall'utente controparte nello scambio
     */
    private @NotNull ExchangeMessage getLastMsg(User f) {
        if (this.counterMessage.getAuthor().equals(f))
            return this.ownerMessage;

        return this.counterMessage;
    }

    public ExchangeMessage getLastMessageByCounterpart(User user) {
        return this.getLastMsg(user);
    }

}
