package demo4.recovery;

import demo4.ui.canvas.MyCanvas;
import demo4.utils.CanvasUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Caretaker implements ICaretaker {
    private Memento memento;

    private final static String BAK_FILE_PATH = "./lastDraw.png";
    public void loadBakFile(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(BAK_FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ois.close();
            memento = (Memento)o;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveBakFile(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(BAK_FILE_PATH)));
            oos.writeObject(memento);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void recovery(){
        loadBakFile();
        MyCanvas canvas = MyCanvas.getInstance();
        canvas.restoreMemento(this.memento);
        CanvasUtils.reset().addImage().redraw().show();
    }
}
