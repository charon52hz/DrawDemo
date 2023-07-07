package demo4.decorator;

import demo4.ui.canvas.MyCanvas;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class BackgroundDecorator extends Decorator {

    public BackgroundDecorator(Import input) {
        super(input);
    }

    @Override
    public void input(String path) {
        super.input(path);
    }

    public void input() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("png,jpg", "png","jpg"));
        JFrame topFrame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, MyCanvas.getInstance());
        int option = fileChooser.showOpenDialog(topFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            input(file.getAbsolutePath());
        }
    }

}
