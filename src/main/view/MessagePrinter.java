package main.view;

import main.controller.Message;
import org.jetbrains.annotations.NotNull;

public class MessagePrinter {
    public static void printText(@NotNull Message text) {
        System.out.println(text.getMessage());
    }

}
