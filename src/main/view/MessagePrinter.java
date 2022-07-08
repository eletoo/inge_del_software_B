package main.view;

import main.controller.PrintableMessage;
import org.jetbrains.annotations.NotNull;

public class MessagePrinter {
    public static void printText(@NotNull PrintableMessage text) {
        System.out.println(text.getMessage());
    }

}
