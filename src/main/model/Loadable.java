package main.model;

import java.io.IOException;

public interface Loadable {

    void load() throws IOException;

    void loadFromFile() throws IOException;
}
