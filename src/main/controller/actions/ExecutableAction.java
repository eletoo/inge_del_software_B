package controller.actions;

import org.jetbrains.annotations.Contract;
import view.Selectable;

public abstract class ExecutableAction implements Selectable {
    private String message;

    @Contract(pure = true)
    public String getMessage() {
        return this.message;
    }

}
