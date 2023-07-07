package demo4.ui.component.pen;

import lombok.Data;

import java.awt.*;

@Data
public class QianPenShape extends PenShape {

    public QianPenShape() {
        super();
    }

    public QianPenShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }
}
