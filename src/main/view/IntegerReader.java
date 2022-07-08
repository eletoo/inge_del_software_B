package main.view;

import main.controller.ErrorMessage;
import main.controller.PrintableMessage;
import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerReader implements IntReader{

    @Override
    public int in(@NotNull PrintableMessage message) {
        while (true) {
            try {
                MessagePrinter.printText(message);
                return new Scanner(System.in).nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                MessagePrinter.printText(ErrorMessage.E_INVALID_INPUT);
            }
        }
    }

}
