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

public class OfferCreator implements UserSelectable {
    @Override
    public void runAction(Controller controller, User user) throws IOException {
        if (controller.getApp().getHierarchiesStore().getHierarchies().isEmpty()) {
            controller.signalToView(ErrorMessage.E_NO_CATEGORIES.getMessage());
            return;
        }

        var cat = controller.getView().choose(GenericMessage.CHOOSE_CATEGORY_TO_PUBLISH,
                controller.getApp().getOffersStore().getLeafCategories(controller.getApp()),
                CategoryEntry::getDisplayName
        );

        var offer = new Offer(controller.askStringFromView(GenericMessage.NAME), (Leaf) cat.getCat(), (Customer) user, OfferState.APERTA);

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
    private void inputField(Controller controller, @NotNull Offer offer, Map.@NotNull Entry<String, NativeField> field) {
        controller.signalToView("Valore per "
                + field.getKey()
                + (field.getValue().isObbligatorio() ? " (Obbligatorio) " : "(Opzionale)")
                + "-- SOLO per campi opzionali: Enter per saltare");

        offer.getFieldsValues()
                .put(
                        field.getKey(),
                        field.getValue().getType().deserialize(
                                controller.askStringFromView(null)
                        )
                );
    }

    @Override
    public String getActionName() {
        return "Crea offerta";
    }
}
