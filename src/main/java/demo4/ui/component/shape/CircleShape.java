package demo4.ui.component.shape;

import java.awt.*;

public class CircleShape extends AbstractShape {
    private int x_start;
    private int y_start;
    private int x_end;
    private int y_end;

    public CircleShape(int x0, int y0, int x1 , int y1 ) {
        this.x_start = x0;
        this.y_start = y0;
        this.x_end = x1;
        this.y_end = y1;
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x_start,y_start,x_end-x_start,y_end-y_start);
    }
}
