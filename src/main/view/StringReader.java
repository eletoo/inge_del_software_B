package main.view;

import main.controller.PrintableMessage;

public interface StringReader {
    String in(PrintableMessage prompt);
}
