package main.controller;

import main.view.View;

import java.util.List;
import java.util.function.Function;

public interface Select {

    Selectable selectAction(View view, List<Selectable> selectableList, Function<Selectable, String> function);
}
