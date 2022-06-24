package main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntegerReader extends Reader{

    public int in() {
        while (true) {
            try {
                return new Scanner(System.in).nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                ErrorMessage.printErrorMessage(ErrorMessage.E_INVALID_INPUT);
            }
        }
    }
}
