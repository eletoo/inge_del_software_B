package main.controller.configuratorActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.model.OfferState;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ExchangingOffersPrinter extends AbstractOffersPrinter{

    public ExchangingOffersPrinter() {
        super(OfferState.IN_SCAMBIO);
    }
    @Override
    public String getActionName() {
        return "Visualizza offerte in scambio";
    }

}
