package demo4.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

@Data
public class PaintConfig extends PaintData implements IPaintConfig {
    /**
     * 画笔
     */
    @Setter
    @Getter
    private static Graphics graphics = null;

    @Override
    public Graphics activate() {
        // 设置颜色
        graphics.setColor(this.color);
        // 同步修改字体大小
        this.font = this.font.deriveFont((float) (this.size.getValue() + 16));
        // 设置字体
        graphics.setFont(this.font);
        // 设置线宽
        setGraphicsSize(this.size.getValue());
        return graphics;
    }
    /**
     * 外部参数激活，不动全局配置参数，用于重绘
     * @param paintData
     * @return
     */
    @Override
    public Graphics activate(PaintData paintData) {
        // 设置颜色
        graphics.setColor(paintData.color);
        // 同步修改字体大小
        this.font = paintData.font.deriveFont((float) (paintData.size.getValue() + 16));
        // 设置字体
        graphics.setFont(paintData.font);
        // 设置线宽
        setGraphicsSize(paintData.size.getValue());
        return graphics;
    }
    @Override
    public void setGraphicsSize(float size) {
        ((Graphics2D) graphics).setStroke(new BasicStroke(size));
    }
    @Override
    public void initGraphics(Graphics g) {
        // 画笔绑定
        graphics = g;
        // 抗锯齿
        // ((Graphics2D) this.graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
    }
    private PaintConfig(){
        // 没有调自己功能，这里直接配好默认字体
        this.font =  new Font("宋体",Font.PLAIN,16);
    }

    private static class Holder{
        private static final PaintConfig INSTANCE = new PaintConfig();
    }

    public static PaintConfig getInstance() {
        return Holder.INSTANCE;
    }

}
