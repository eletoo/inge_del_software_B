package main.view;

import main.controller.Select;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class ActionSelection implements Select {

    @Override
    public Selectable selectAction(List<Selectable> list, Function<Selectable, String> function) {
        return this.choose(list, function);
    }

    public <T> void showList(@NotNull List<T> list) {
        this.showList(list, null);
    }

    public <T> void showList(@NotNull List<T> list, Function<T, String> function) {
        list.forEach(e -> MessagePrinter.printText(function == null ? e.toString() : function.apply(e)));
    }

    private <T> T choose(@NotNull List<T> selections, Function<T, String> function) {
        assert selections.size() > 0;
        MessagePrinter.printText("\nSelezionare un indice.");
        int i = 0;
        for (var o : selections)
            MessagePrinter.printText(i++ + ") " + (function == null ? o.toString() : function.apply(o)));

        int input = -1;
        IntegerReader integerReader = new IntegerReader();
        while ((input = integerReader.in()) < 0 || input >= selections.size()) ;
        return selections.get(input);
    }

}
