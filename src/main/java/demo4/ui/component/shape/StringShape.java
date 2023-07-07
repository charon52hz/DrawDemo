package demo4.ui.component.shape;

import java.awt.*;

public class StringShape extends AbstractShape {

    public StringShape() {
        super();
    }

    public StringShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawString(this.getWordText(), this.getX1(), this.getY1());
    }
}
