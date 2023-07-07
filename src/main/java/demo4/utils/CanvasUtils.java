package demo4.utils;

import demo4.config.PaintConfig;
import demo4.ui.canvas.IMyCanvas;
import demo4.ui.canvas.MyCanvas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasUtils {

    public static final IMyCanvas canvas = MyCanvas.getInstance();
    public static final PaintConfig paintConfig = PaintConfig.getInstance();


    /**
     * 添加背景图片
     */
    public static CanvasUtils addImage(BufferedImage image){
        Graphics graphics = canvas.updateImage(CloneUtils.copyImage(image));
        MyCanvas.setImportImage(image);
        paintConfig.initGraphics(graphics);
        canvas.magic();
        return null;
    }
    /**
     * 恢复背景图片，用于重置口图片丢失的修复
     * 当初设计没考虑到的细节
     * TODO 重构思路，背景图片作为画布属性参与初始化。
     */
    public static CanvasUtils addImage(){
        if (MyCanvas.getImportImage() == null){
            return null;
        }
        Graphics graphics = canvas.updateImage(CloneUtils.copyImage(MyCanvas.getImportImage()));
        paintConfig.initGraphics(graphics);
        canvas.magic();
        return null;
    }

    /**
     * 删除背景图片
     */
    public static CanvasUtils removeImage(){
        MyCanvas.setImportImage(null);
        return null;
    }
    /**
     * 清空画板
     */
    public static CanvasUtils reset(){
        Graphics graphics = canvas.reset();
        paintConfig.initGraphics(graphics);
        return null;
    }

    /**
     * 重绘
     */
    public static CanvasUtils redraw(){
        canvas.redraw();
        return null;
    }

    /**
     * 使用指定画笔重绘
     */
    public static CanvasUtils redraw(Graphics graphics){
        canvas.redraw(graphics);
        return null;
    }

    /**
     * 显示
     */
    public static CanvasUtils show(){
        canvas.magic();
        return null;
    }
    /**
     * 在重置之后获取新的画笔，链式调用的尾部调用最合适
     */
    public static Graphics getGraphicsAfterReset(){
        return paintConfig.activate();
    }

}
