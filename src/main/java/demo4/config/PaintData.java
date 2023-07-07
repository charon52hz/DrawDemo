package demo4.config;

import demo4.DrawConst;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;

@Data
public abstract class PaintData implements Serializable {

    private final static long serialVersionUID = 1L;
    /**
     * 画笔颜色
     */
    protected Color color;
    /**
     * 画笔尺寸
     */
    protected DrawConst.BrushSizeEnum size;
    /**
     * 画笔风格
     */
    protected DrawConst.BrushStyleEnum style;
    /**
     * 文本框的数据
     */
    protected String wordText;
    /**
     * 主题背景色，用于橡皮擦
     */
    protected Color backgroundColor;
    /**
     * 字体
     */
    protected Font font;
}
