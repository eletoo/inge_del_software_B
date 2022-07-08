package main.view;

import main.controller.PrintableMessage;
import main.controller.Select;
import main.controller.Selectable;
import org.jetbrains.annotations.NotNull;


import java.util.List;
import java.util.function.Function;

public class ActionSelection implements Select {

    @Override
    public Selectable selectAction(@NotNull View view, List<Selectable> list, Function<Selectable, String> function) {
        return view.choose(list, function);
    }


}
