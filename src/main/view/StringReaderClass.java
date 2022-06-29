package main.view;

import main.controller.GenericMessage;

import java.util.Scanner;

public class StringReaderClass implements StringReader {

    public String in(GenericMessage prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            MessagePrinter.printText(prompt.getMessage());
            s = new Scanner(System.in).next();
        }
        return s;
    }
}
