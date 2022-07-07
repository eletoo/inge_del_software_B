package main.controller.configuratorActions;

import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.model.*;
import main.controller.Controller;
import main.controller.UserSelectable;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ClosedOffersPrinter extends AbstractOffersPrinter {
    public ClosedOffersPrinter() {
        super(OfferState.CHIUSA);
    }

    @Override
    public String getActionName() {
        return "Visualizza offerte chiuse";
    }
}
