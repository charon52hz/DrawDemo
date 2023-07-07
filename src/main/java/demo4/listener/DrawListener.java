package demo4.listener;

import demo4.DrawConst;
import demo4.command.Command;
import demo4.command.ShapeCommand;
import demo4.ui.component.pen.*;
import demo4.ui.component.shape.*;
import demo4.utils.CanvasUtils;
import lombok.Data;

import java.awt.*;
import java.awt.event.MouseEvent;

@Data
public class DrawListener extends ListenerAdapter {
    private Integer X1, X2, Y1, Y2;
    private Command command = new ShapeCommand();

    @Override
    public void mouseDragged(MouseEvent e) {
        // 橡皮擦
        if (DrawConst.BrushStyleEnum.ERASE.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new EraseShape(X1, Y1, X2, Y2), true);
            updateXY1();
        }
        // 铅笔
        else if (DrawConst.BrushStyleEnum.PEN_QIAN.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new QianPenShape(X1, Y1, X2, Y2), true);
            updateXY1();
        }
        // 蜡笔
        else if (DrawConst.BrushStyleEnum.PEN_LA.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new LaPenShape(X1, Y1, X2, Y2), true);
            updateXY1();
        }
        // 毛笔
        else if (DrawConst.BrushStyleEnum.PEN_MAO.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new MaoPenShape(X1, Y1, X2, Y2), true);
            updateXY1();
        } else {
            // 为了实现动态刷新
            Graphics graphics = CanvasUtils.reset().addImage().redraw().getGraphicsAfterReset();
            updateXY2(e);
            AbstractShape shape = null;
            if (DrawConst.BrushStyleEnum.LINE.equals(paintConfig.getStyle())) {
                shape = new LineShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.RECT.equals(paintConfig.getStyle())) {
                shape = new RectShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.RECT_FILL.equals(paintConfig.getStyle())) {
                shape = new RectFillShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.CIRCLE.equals(paintConfig.getStyle())) {
                shape = new CircleShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.CIRCLE_FILL.equals(paintConfig.getStyle())) {
                shape = new CircleFillShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.ROUND.equals(paintConfig.getStyle())) {
                shape = new RectRoundShape(X1, Y1, X2, Y2);
            } else if (DrawConst.BrushStyleEnum.ROUND_FILL.equals(paintConfig.getStyle())) {
                shape = new RectRoundFillShape(X1, Y1, X2, Y2);
            }
            if (shape != null) {
                shape.show(graphics);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 文字模式
        if (DrawConst.BrushStyleEnum.WORD.equals(paintConfig.getStyle())) {
            // 如果是单击
            if (e.getClickCount() == 1) {
                updateXY2(e);
                command.execute(new StringShape(X1, Y1, X2, Y2), true);
                updateXY1();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        X1 = e.getX();
        Y1 = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // 直线
        if (DrawConst.BrushStyleEnum.LINE.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new LineShape(X1, Y1, X2, Y2), true);
        }
        // 圆形
        else if (DrawConst.BrushStyleEnum.CIRCLE.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new CircleShape(X1, Y1, X2, Y2), true);
        }
        // 填充圆形
        else if (DrawConst.BrushStyleEnum.CIRCLE_FILL.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new CircleFillShape(X1, Y1, X2, Y2), true);
        }
        // 矩形
        else if (DrawConst.BrushStyleEnum.RECT.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new RectShape(X1, Y1, X2, Y2), true);
        }
        // 填充矩形
        else if (DrawConst.BrushStyleEnum.RECT_FILL.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new RectFillShape(X1, Y1, X2, Y2), true);
        }
        // 填充圆形
        else if (DrawConst.BrushStyleEnum.ROUND.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new RectRoundShape(X1, Y1, X2, Y2), true);
        }
        // 填充圆角填充矩形
        else if (DrawConst.BrushStyleEnum.ROUND_FILL.equals(paintConfig.getStyle())) {
            updateXY2(e);
            command.execute(new RectRoundFillShape(X1, Y1, X2, Y2), true);
        } else {
            // 橡皮擦            // 铅笔            // 蜡笔            // 毛笔
            if (DrawConst.BrushStyleEnum.ERASE.equals(paintConfig.getStyle())
                    || DrawConst.BrushStyleEnum.PEN_QIAN.equals(paintConfig.getStyle())
                    || DrawConst.BrushStyleEnum.PEN_LA.equals(paintConfig.getStyle())
                    || DrawConst.BrushStyleEnum.PEN_MAO.equals(paintConfig.getStyle())) {
                command.execute( new PenShape(), true);
            }
        }
    }
    private void updateXY1(){
        X1 = X2;
        Y1 = Y2;
    }

    private void updateXY2(MouseEvent e){
        X2 = e.getX();
        Y2 = e.getY();
    }

    private void checkXY(){

    }

    private DrawListener() {

    }

    private static class Holder {
        private static final DrawListener INSTANCE = new DrawListener();
    }

    public static DrawListener getInstance() {
        return DrawListener.Holder.INSTANCE;
    }
}
