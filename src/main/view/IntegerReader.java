package main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerReader implements IntReader{

    @Override
    public int in(GenericMessage message) {
        while (true) {
            try {
                return new Scanner(System.in).nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                ErrorMessage.printErrorMessage(ErrorMessage.E_INVALID_INPUT);
            }
        }
    }
}
