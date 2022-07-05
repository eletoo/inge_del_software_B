package main.model;

import java.io.IOException;
import java.io.Serializable;

public interface Saveable {

    void save() throws IOException;

    void saveOnFile(Serializable s);
}
