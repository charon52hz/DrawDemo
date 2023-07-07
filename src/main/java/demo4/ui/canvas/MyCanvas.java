package demo4.ui.canvas;

import demo4.config.PaintConfig;
import demo4.recovery.Memento;
import demo4.ui.component.shape.AbstractShape;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Data
public final class MyCanvas extends JPanel implements IMyCanvas {

    private final static long serialVersionUID = 1L;
    /**
     * 存储图形
     */
    private List<AbstractShape> shapes = new ArrayList<AbstractShape>();
    //private List<AbstractShape> history = new ArrayList<AbstractShape>();
    /**
     * 绑定画笔配置
     */
    @Getter
    private static final PaintConfig paintConfig = PaintConfig.getInstance();
    /**
     * 使用图片绘制，更为丝滑
     */
    @Getter
    @Setter
    private static BufferedImage image = null;

    @Getter
    @Setter
    private static BufferedImage importImage = null;

    @Override
    public void paint(Graphics g) {
        // super.paint(g);
        g.drawImage(image, 0, 0, null);
    }
    /**
     * 重置画板
     */
    public Graphics reset(Color color) {
        // 创建新的图片画布
        MyCanvas.setImage(new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB));
        // 获取画笔，画笔用于在画布上进行绘制
        Graphics g = image.getGraphics();
        // 设置画笔的颜色
        assert color != null;
        g.setColor(color);
        // 绘制画布的背景色
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // 更改canvas 的背景
        this.setBackground(color);
        return g;
    }

    public Graphics updateImage(BufferedImage bufferedImage) {
        if (bufferedImage != null) {
            MyCanvas.setImage(bufferedImage);
        }
        return image.getGraphics();
    }

    public Graphics reset() {
        return reset(paintConfig.getBackgroundColor());
    }

    public void redraw() {
        redraw(paintConfig.activate());
    }

    public void redraw(Graphics graphics) {
        shapes.forEach(shape -> {
            shape.show(graphics, false);
        });
    }


    @Override
    public void magic() {
        this.repaint();
    }


    @Override
    public Memento createMemento() {
        return new Memento(shapes);
    }

    @Override
    public void restoreMemento(Memento memento) {
        shapes.clear();
        shapes.addAll(memento.getShapes());
    }

    // =====================================================================
    private MyCanvas() {
    }


    private static class Holder {
        private static final MyCanvas INSTANCE = new MyCanvas();
    }

    public static MyCanvas getInstance() {
        return MyCanvas.Holder.INSTANCE;
    }
}
