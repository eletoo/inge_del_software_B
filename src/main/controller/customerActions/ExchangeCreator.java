package main.controller.customerActions;

import main.Application;
import main.controller.*;
import main.exceptions.InvalidMethodException;
import main.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.stream.Collectors;

public class ExchangeCreator implements Selectable, ListSelect {

    @Override
    public void runAction(@NotNull Application app, User user) throws IOException {

        if (app.getHierarchiesStore().getHierarchies().isEmpty()) {
            Controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        if (app.getInformationStore().getInformation() == null) {
            Controller.signalToView(ErrorMessage.E_NO_INFO.getMessage());
            return;
        }

        var sc = createExchange(app, (Customer) user);
        if (sc != null) {
            app.getExchangesStore().addExchange(sc);
            app.getExchangesStore().save();
        }
    }

    @Override
    public void runAction(Application app) {
        throw new InvalidMethodException();
    }

    @Override
    public String getActionName() {
        return "Crea proposta di scambio";
    }


    private @Nullable Exchange createExchange(@NotNull Application app, @NotNull Customer author) {
        if (app.getOffersStore().getOffers(author, OfferState.APERTA).isEmpty()) {
            Controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
            return null;
        }

        Controller.signalToView(GenericMessage.CHOOSE_OFFER.getMessage());

        var ownOffer = choose(app.getOffersStore().getOffers(author)
                .stream()
                .filter(Offer::isAvailableOffer)
                .collect(Collectors.toList()), null);

        var possible_offers = app.getOffersStore().getOffers(ownOffer.getCategory())
                .stream()
                .filter(e -> !ownOffer.getOwner().equals(e.getOwner()))
                .filter(Offer::isAvailableOffer)
                .collect(Collectors.toList());

        if (possible_offers.isEmpty()) {
            Controller.signalToView(ErrorMessage.E_NO_OFFERS.getMessage());
            return null;
        }

        Controller.signalToView(GenericMessage.CHOOSE_OTHER_OFFER.getMessage());

        var selectedOffer = choose(possible_offers, null);

        return new Exchange(app, ownOffer, selectedOffer);
    }
}
