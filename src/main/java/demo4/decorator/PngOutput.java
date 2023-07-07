package demo4.decorator;

import demo4.ui.canvas.MyCanvas;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PngOutput implements Output{


    @Override
    public void output(String path) {
        BufferedImage image = MyCanvas.getImage();
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
