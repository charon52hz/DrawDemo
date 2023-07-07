package demo4.config;

import java.awt.*;

public interface IPaintConfig {
    /**
     * 激活配置
     */
    Graphics activate();
    /**
     * 使用paintData激活
     */
    Graphics activate(PaintData paintData);
    /**
     * 初始化画笔
     */
    void initGraphics(Graphics g);
    /**
     * 设置画笔尺寸
     */
    void setGraphicsSize(float size);
}
