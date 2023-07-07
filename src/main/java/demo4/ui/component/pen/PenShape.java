package demo4.ui.component.pen;

import demo4.ui.component.shape.AbstractShape;
import lombok.Data;

import java.awt.*;

@Data
public class PenShape extends AbstractShape {
    public PenShape() {
        super();
    }

    public PenShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics graphics) {
    }
}
