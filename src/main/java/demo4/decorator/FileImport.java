package demo4.decorator;

import demo4.recovery.Memento;
import demo4.ui.canvas.MyCanvas;
import demo4.utils.CanvasUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileImport implements Import{

    @Override
    public void input(String path) {
        // TODO 由于迁移了备忘录模式的恢复逻辑，背景图片无法通过备忘录保存
        FileInputStream fis = null;
        Memento memento;
        try {
            fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ois.close();
            memento = (Memento)o;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        MyCanvas canvas = MyCanvas.getInstance();
        canvas.restoreMemento(memento);
        CanvasUtils.reset().redraw().show();
    }
}
