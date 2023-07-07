package demo4.command;

import demo4.ui.component.shape.AbstractShape;

public interface Command {
    void execute(AbstractShape shape, Boolean isShow);

    void undo(Boolean isShow);

    void redo(Boolean isShow);
}