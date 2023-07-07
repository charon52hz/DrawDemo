package demo4.ui.component.pen;

import demo4.ui.canvas.MyCanvas;

import java.awt.*;

public class EraseShape extends PenShape {

    public EraseShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void specialSolveGraphics(Graphics graphics) {
        // 设置颜色
        graphics.setColor(this.getPaintConfig().getBackgroundColor());
        // 设置线宽
        this.getPaintConfig().setGraphicsSize(14 + this.size.getValue());
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }
}