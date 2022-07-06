package main.view;

import main.controller.Message;

import java.util.Scanner;

public class StringReaderClass implements StringReader {

    public String in(Message prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            MessagePrinter.printText(prompt.getMessage());
            s = new Scanner(System.in).next();
        }
        return s;
    }

    public String inLine(Message prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            MessagePrinter.printText(prompt.getMessage());
            s = new Scanner(System.in).next();
        }
        return s;
    }

    public String inPotentiallyEmptyLine(Message prompt) {

        MessagePrinter.printText(prompt.getMessage());
        return new Scanner(System.in).next();

    }
}
