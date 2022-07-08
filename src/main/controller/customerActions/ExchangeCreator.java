package main.controller.customerActions;

import main.controller.*;
import main.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * crea uno scambio
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class ExchangeCreator implements UserSelectable {
    /**
     * chiama un metodo per creare uno scambio facendo selezionare la propria offerta e quella con cui scambiarla
     * @param controller controller
     * @param user utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {

        if (controller.getApp().getHierarchiesStore().getHierarchies().isEmpty()) {
            controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        if (controller.getApp().getInformationStore().getInformation() == null) {
            controller.signalToView(ErrorMessage.E_NO_INFO.getMessage());
            return;
        }

        var sc = createExchange(controller, (Customer) user);
        if (sc != null) {
            controller.getApp().getExchangesStore().addExchange(sc);
            controller.getApp().save();
        }
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Crea proposta di scambio";
    }

    /**
     * crea uno scambio
     * @param controller controller
     * @param author autore dello scambio
     * @return scambio
     */
    private @Nullable Exchange createExchange(@NotNull Controller controller, @NotNull Customer author) {
        if (controller.getApp().getOffersStore().getOffers(author, OfferState.APERTA).isEmpty()) {
            controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
            return null;
        }

        var ownOffer = controller.getView().choose(GenericMessage.CHOOSE_OFFER, controller.getApp().getOffersStore().getOffers(author)
                .stream()
                .filter(Offer::isAvailableOffer)
                .collect(Collectors.toList()), Offer::getName);

        var possible_offers = controller.getApp().getOffersStore().getOffers(ownOffer.getCategory())
                .stream()
                .filter(e -> !ownOffer.getOwner().equals(e.getOwner()))
                .filter(Offer::isAvailableOffer)
                .collect(Collectors.toList());

        if (possible_offers.isEmpty()) {
            controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
            return null;
        }

        var selectedOffer = controller.getView().choose(GenericMessage.CHOOSE_OTHER_OFFER, possible_offers, Offer::getName);

        return new Exchange(ownOffer, selectedOffer);
    }
}
