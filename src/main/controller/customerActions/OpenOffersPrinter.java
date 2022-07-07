package main.controller.customerActions;

import main.model.Application;
import main.controller.Controller;
import main.controller.UserSelectable;
import main.exceptions.InvalidMethodException;
import main.model.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class OpenOffersPrinter extends main.controller.configuratorActions.OpenOffersPrinter {
    @Override
    public String getActionName() {
        return "Visualizza offerte aperte per categoria";
    }
}
