package main.view;

import main.controller.ListSelect;
import main.controller.Select;
import main.controller.Selectable;


import java.util.List;
import java.util.function.Function;

public class ActionSelection implements Select , ListSelect {

    @Override
    public Selectable selectAction(List<Selectable> list, Function<Selectable, String> function) {
        return choose(list, function);
    }



}
