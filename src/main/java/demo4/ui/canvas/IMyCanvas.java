package demo4.ui.canvas;

import demo4.recovery.Memento;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IMyCanvas {
    /**
     * 重置画板为指定颜色
     */
    Graphics reset(Color color);
    /**
     * 指定图片与背景色
     */
    Graphics updateImage(BufferedImage bufferedImage);

    /**
     * 重置画板为全局唯一配置的背景颜色
     */
    Graphics reset();

    /**
     * 通过画笔绘制存储的图形
     */
    void redraw(Graphics graphics);
    /**
     * 使用全局配置中的画笔绘制存储的图形
     */
    void redraw();
    /**
     * 显示图形
     */
    void magic();
    /**
     * 创建备忘录
     */
    Memento createMemento();

    void restoreMemento(Memento memento);
    /**
     * 从备忘录中恢复
     */

}
