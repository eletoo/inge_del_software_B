package main.controller.customerActions;

import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.exceptions.RequiredConstraintFailureException;
import main.model.*;
import main.controller.Controller;
import main.controller.UserSelectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * crea un'offerta
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class OfferCreator implements UserSelectable {
    /**
     * crea un'offerta chiedendo all'utente di compilare i campi necessari
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

        var cat = controller.getView().choose(GenericMessage.CHOOSE_CATEGORY_TO_PUBLISH,
                controller.getApp().getOffersStore().getLeafCategories(controller.getApp()),
                CategoryEntry::getDisplayName
        );

        var offer = new Offer(controller.askLineFromView(GenericMessage.NAME), (Leaf) cat.getCat(), (Customer) user, OfferState.APERTA);

        for (var field : cat.getCat().getNativeFields().entrySet()) {
            inputField(controller, offer, field);
        }

        try {
            controller.getApp().getOffersStore().addOffer(offer);
            controller.getApp().getOffersStore().save();
        } catch (RequiredConstraintFailureException e) {
            e.printStackTrace(); //should not happen
        }
    }

    /**
     * Permette l'inserimento del valore di un campo
     *
     * @param offer offerta
     * @param field campo da compilare
     */
    private void inputField(@NotNull Controller controller, @NotNull Offer offer, Map.@NotNull Entry<String, NativeField> field) {
        controller.signalToView("Valore per "
                + field.getKey()
                + (field.getValue().isObbligatorio() ? " (Obbligatorio) " : "(Opzionale)")
        );

        offer.getFieldsValues()
                .put(
                        field.getKey(),
                        field.getValue().getType().deserialize(
                                field.getValue().isObbligatorio() ?
                                        controller.askLineFromView(GenericMessage.OPTIONAL_FIELD)
                                        : controller.askPotentiallyEmptyStringFromView(GenericMessage.OPTIONAL_FIELD)
                        )
                );
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Crea offerta";
    }
}
