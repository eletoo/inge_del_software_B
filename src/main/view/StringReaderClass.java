package main.view;

import java.util.Scanner;

public class StringReaderClass implements StringReader {

    public String in(GenericMessage prompt) {
        String s = null;
        while (s == null || s.equalsIgnoreCase("")) {
            GenericMessage.printGenericMessage(prompt);
            s = new Scanner(System.in).next();
        }
        return s;
    }
}
