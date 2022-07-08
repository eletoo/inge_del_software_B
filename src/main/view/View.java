package main.view;

import main.controller.CustomMessage;
import main.controller.GenericMessage;
import main.controller.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class View {

    public <T> void showList(@NotNull List<T> list, Function<T, Message> function) {
        list.forEach(e -> MessagePrinter.printText(new CustomMessage(function == null ? e.toString() : function.apply(e).getMessage())));
    }

    public <T> T choose(Message prompt, @NotNull List<T> selections, Function<T, Message> function) {
        this.notify(prompt);
        return this.choose(selections, function);
    }

    public <T> T choose(@NotNull List<T> selections, Function<T, Message> function) {
        assert selections.size() > 0;
        //MessagePrinter.printText("\nSelezionare un indice.");
        int i = 0;
        for (var o : selections)
            MessagePrinter.printText(new CustomMessage(i++ + ") " + (function == null ? o.toString() : function.apply(o))));

        int input = -1;
        IntegerReader integerReader = new IntegerReader();
        while ((input = integerReader.in(GenericMessage.SELECT_INDEX)) < 0 || input >= selections.size()) ;
        return selections.get(input);
    }

    private void printText(String text) {
        System.out.println(text);
    }

    public void notify(String text) {
        this.printText(text);
    }

    public void notify(@NotNull Message m) {
        this.notify(m.getMessage());
    }

}
