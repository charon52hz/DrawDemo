package demo4.decorator;

import demo4.ui.canvas.MyCanvas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class PngDecorator extends Decorator {

    public PngDecorator(Output output) {
        super(output);
    }

    @Override
    public void output(String path) {
        super.output(path);
    }

    public void output() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
        JFrame topFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, MyCanvas.getInstance());
        int option = fileChooser.showOpenDialog(topFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            output(file.getAbsolutePath()+".png");
        }
    }

}
