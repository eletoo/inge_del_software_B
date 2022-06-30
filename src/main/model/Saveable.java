package main.model;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Saveable {

    void save() throws IOException;
}
