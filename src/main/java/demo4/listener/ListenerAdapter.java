package demo4.listener;

import demo4.DrawConst;
import demo4.config.PaintConfig;
import demo4.recovery.Caretaker;
import demo4.recovery.CaretakerProxy;
import demo4.recovery.ICaretaker;
import demo4.ui.canvas.MyCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class ListenerAdapter implements MouseListener, MouseMotionListener, ActionListener {
    /**
     * 绑定画笔配置
     */
    protected final PaintConfig paintConfig = PaintConfig.getInstance();
    /**
     * 绑定画板
     */
    protected final MyCanvas canvas = MyCanvas.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        ICaretaker caretaker = (ICaretaker)new CaretakerProxy(new Caretaker()).getProxy();
        caretaker.setMemento(canvas.createMemento());
        caretaker.saveBakFile();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        refreshCursor(e);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        putCursor(e, Cursor.DEFAULT_CURSOR);
    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    private void refreshCursor(MouseEvent e) {
        // 插入文字
        if (DrawConst.BrushStyleEnum.WORD.equals(this.paintConfig.getStyle())) {
            putCursor(e, Cursor.TEXT_CURSOR);
        }
        // 橡皮
        else if (DrawConst.BrushStyleEnum.ERASE.equals(this.paintConfig.getStyle())) {
            putCursor(e, Cursor.HAND_CURSOR);
        }
        // 其他形状
        else {
            putCursor(e, Cursor.CROSSHAIR_CURSOR);
        }
    }
    private void putCursor(MouseEvent e, Integer i) {
        JFrame topFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, e.getComponent());
        topFrame.setCursor(new Cursor(i));
    }
}
