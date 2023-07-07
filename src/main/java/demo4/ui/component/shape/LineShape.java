package demo4.ui.component.shape;

import java.awt.*;

public class LineShape extends AbstractShape {
    public LineShape() {
        super();
    }

    public LineShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }
}
