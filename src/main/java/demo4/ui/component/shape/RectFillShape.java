package demo4.ui.component.shape;

import java.awt.*;

public class RectFillShape extends AbstractShape {
    public RectFillShape() {
        super();
    }

    public RectFillShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.fillRect(this.getX1(), this.getY1(), this.getX2() - this.getX1(), this.getY2() - this.getY1());
    }
}
