package demo4.ui.component.shape;

import demo4.config.PaintConfig;
import demo4.config.PaintData;
import demo4.ui.canvas.MyCanvas;
import lombok.Data;

import java.awt.*;

@Data
public abstract class AbstractShape extends PaintData {
    private Integer X1, Y1, X2, Y2;

    private final MyCanvas canvas = MyCanvas.getInstance();
    private final PaintConfig paintConfig = PaintConfig.getInstance();


    public AbstractShape() {
        this.font = paintConfig.getFont();
        this.color = paintConfig.getColor();
        this.size = paintConfig.getSize();
        this.style = paintConfig.getStyle();
        this.wordText = paintConfig.getWordText();
        this.backgroundColor = paintConfig.getBackgroundColor();
    }

    public AbstractShape(int x1, int y1, int x2, int y2) {
        this();
        this.X1 = x1;
        this.X2 = x2;
        this.Y1 = y1;
        this.Y2 = y2;
    }

    public void specialSolveGraphics(Graphics graphics) {
    }

    public abstract void draw(Graphics graphics);

    public void show(Graphics graphics) {
        show(graphics, true);
    }

    public void show(Graphics graphics, Boolean isRepaint) {
        // * 刷新画笔配置
        activateGraphics();
        // * 对画笔特殊处理
        specialSolveGraphics(graphics);
        // * 定制化
        draw(graphics);
        if (isRepaint) {
            // * 绘制
            repaint();
        }
    }

    private void activateGraphics() {
        paintConfig.activate(this);
    }

    public void repaint() {
        canvas.repaint();
    }
}