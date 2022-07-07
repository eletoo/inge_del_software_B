package main.view;

import main.controller.GenericMessage;
import main.controller.ListSelect;
import main.controller.Message;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class View implements ListSelect //todo remove
{

    public <T> void showList(@NotNull List<T> list) {
        this.showList(list, null);
    }

    public <T> void showList(@NotNull List<T> list, Function<T, String> function) {
        list.forEach(e -> MessagePrinter.printText(function == null ? e.toString() : function.apply(e)));
    }

    public <T> T choose(Message prompt, @NotNull List<T> selections, Function<T, String> function) {
        this.notify(prompt);
        return this.choose(selections, function);
    }

    public <T> T choose(@NotNull List<T> selections, Function<T, String> function) {
        assert selections.size() > 0;
        //MessagePrinter.printText("\nSelezionare un indice.");
        int i = 0;
        for (var o : selections)
            MessagePrinter.printText(i++ + ") " + (function == null ? o.toString() : function.apply(o)));

        int input = -1;
        IntegerReader integerReader = new IntegerReader();
        while ((input = integerReader.in(GenericMessage.SELECT_INDEX)) < 0 || input >= selections.size()) ;
        return selections.get(input);
    }

    private void printText(String text) {
        System.out.println(text);
    }

    public void notify(String text){
        this.printText(text);
    }

    public void notify(Message m){
        this.notify(m.getMessage());
    }

}
