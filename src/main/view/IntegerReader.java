package main.view;

import main.controller.ErrorMessage;
import main.controller.Message;
import org.jetbrains.annotations.NotNull;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerReader implements IntReader{

    @Override
    public int in(@NotNull Message message) {
        while (true) {
            try {
                MessagePrinter.printText(message.getMessage());
                return new Scanner(System.in).nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                MessagePrinter.printText(ErrorMessage.E_INVALID_INPUT.getMessage());
            }
        }
    }

}
