package main.controller.structures;

import main.Application;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ExchangeStructure implements StructureLoader {

    @Override
    public void prepareStructure(@NotNull Application app) throws IOException {
        app.getExchangesStore().load();
    }
}
