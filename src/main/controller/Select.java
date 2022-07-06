package main.controller;

import java.util.List;
import java.util.function.Function;

public interface Select {

    Selectable selectAction(List<Selectable> selectableList, Function<Selectable, String> function);
}
