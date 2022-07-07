package main.controller;

import java.io.IOException;

public interface Selectable {

    void runAction(Controller controller) throws IOException;

    String getActionName();
}
