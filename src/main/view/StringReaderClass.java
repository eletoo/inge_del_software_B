package main.view;

import main.controller.PrintableMessage;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class StringReaderClass implements StringReader {

    public String in(PrintableMessage prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            MessagePrinter.printText(prompt);
            s = new Scanner(System.in).next();
        }
        return s;
    }

    public String inLine(PrintableMessage prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            MessagePrinter.printText(prompt);
            s = new Scanner(System.in).nextLine();
        }
        return s;
    }

    public String inPotentiallyEmptyLine(@NotNull PrintableMessage prompt) {
        MessagePrinter.printText(prompt);
        return new Scanner(System.in).nextLine();
    }
}
