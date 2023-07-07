package demo4.ui.component.pen;

import lombok.Data;

import java.awt.*;

@Data
public class MaoPenShape extends PenShape {
    public MaoPenShape() {
        super();
    }

    public MaoPenShape(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void draw(Graphics graphics) {
        Integer x1 = this.getX1();
        Integer y1 = this.getY1();
        Integer x2 = this.getX2();
        Integer y2 = this.getY2();
        Integer value = 10 + (int) (this.getPaintConfig().getSize().getValue() * 1.5);
        // 如果2 在第四象限
        if (x2 >= x1 && y2 >= y1) {
            x1 = x1 - value;
            y1 = y1 - value;
            x2 = x2 + value;
            y2 = y2 + value;
            graphics.fillOval(x1, y1, x2 - x1, y2 - y1);
        }
        // 2在第二象限
        else if (x2 <= x1 && y2 <= y1) {
            x1 = x1 + value;
            y1 = y1 + value;
            x2 = x2 - value;
            y2 = y2 - value;
            graphics.fillOval(x2, y2, x1 - x2, y1 - y2);
        }
        // 2 在第一象限
        else if (x2 >= x1 && y2 <= y1) {
            x1 = x1 - value;
            y1 = y1 + value;
            x2 = x2 + value;
            y2 = y2 - value;
            graphics.fillOval(x1, y2, x2 - x1, y1 - y2);
        }
        // 2 在第三象限
        else if (x2 <= x1 && y2 >= y1) {
            x1 = x1 + value;
            y1 = y1 - value;
            x2 = x2 - value;
            y2 = y2 + value;
            graphics.fillOval(x2, y1, x1 - x2, y2 - y1);
        }
    }
}
