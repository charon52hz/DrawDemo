package demo4.decorator;

import demo4.ui.canvas.MyCanvas;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOutput implements Output{


    @Override
    public void output(String path) {
        MyCanvas canvas = MyCanvas.getInstance();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(path)));
            oos.writeObject(canvas.createMemento());
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
